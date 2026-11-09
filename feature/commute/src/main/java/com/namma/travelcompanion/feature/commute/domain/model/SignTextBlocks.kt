package com.namma.travelcompanion.feature.commute.domain.model

/** A run of Kannada text OCR found on a frozen frame; box in that bitmap's pixels. */
data class SignTextBlock(val text: String, val left: Int, val top: Int, val right: Int, val bottom: Int) {
    val width get() = right - left
    val height get() = bottom - top
}

object SignTextBlocks {
    private val kannadaLetter = Regex("[\u0C80-\u0CFF]")

    /**
     * Turns Tesseract text lines into translatable blocks: drops lines with fewer than two Kannada
     * characters (OCR noise on edges, logos), joins stacked lines of the same paragraph so a two-line
     * board translates as one sentence, and returns them in reading order (top to bottom, then left to right).
     */
    fun merge(lines: List<SignTextBlock>): List<SignTextBlock> {
        val kept = lines
            .map { it.copy(text = it.text.trim().replace(Regex("\\s+"), " ")) }
            .filter { kannadaLetter.findAll(it.text).count() >= 2 }
            .sortedWith(compareBy({ it.top }, { it.left }))
        val blocks = mutableListOf<SignTextBlock>()
        for (line in kept) {
            val i = blocks.indexOfLast { it.continuesInto(line) }
            if (i >= 0) blocks[i] = blocks[i].joinedWith(line) else blocks += line
        }
        return readingOrder(blocks)
    }

    /** Rows first (a block whose top is above the row's first block's vertical middle shares its row), then left to right. */
    private fun readingOrder(blocks: List<SignTextBlock>): List<SignTextBlock> {
        val rows = mutableListOf<MutableList<SignTextBlock>>()
        for (block in blocks.sortedBy { it.top }) {
            val row = rows.lastOrNull()
            if (row != null && block.top < row.first().let { it.top + it.height / 2 }) row += block else rows += mutableListOf(block)
        }
        return rows.flatMap { row -> row.sortedBy { it.left } }
    }

    /** [next] sits just below this block and mostly under it horizontally. */
    private fun SignTextBlock.continuesInto(next: SignTextBlock): Boolean {
        val lineHeight = minOf(height, next.height)
        val gap = next.top - bottom
        val overlapX = minOf(right, next.right) - maxOf(left, next.left)
        return gap > -lineHeight / 2 && gap < lineHeight * 0.6 && overlapX > minOf(width, next.width) / 2
    }

    private fun SignTextBlock.joinedWith(next: SignTextBlock) = SignTextBlock(
        text = "$text ${next.text}",
        left = minOf(left, next.left),
        top = minOf(top, next.top),
        right = maxOf(right, next.right),
        bottom = maxOf(bottom, next.bottom)
    )
}
