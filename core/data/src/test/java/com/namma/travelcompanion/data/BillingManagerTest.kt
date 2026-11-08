package com.namma.travelcompanion.data

import android.content.Context
import android.content.SharedPreferences
import com.namma.travelcompanion.data.billing.BillingManager
import com.namma.travelcompanion.domain.model.PlanTier
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BillingManagerTest {

    private val context = mockk<Context>(relaxed = true)
    private val prefs = mockk<SharedPreferences>(relaxed = true)
    private val editor = mockk<SharedPreferences.Editor>(relaxed = true)

    @Before
    fun setup() {
        every { context.getSharedPreferences(any(), any()) } returns prefs
        every { prefs.edit() } returns editor
        every { editor.putString(any(), any()) } returns editor
        every { editor.putInt(any(), any()) } returns editor
        every { editor.putBoolean(any(), any()) } returns editor
        every { editor.remove(any()) } returns editor
    }

    @Test
    fun `initial free tier gives default scans and phrases`() {
        every { prefs.getString("last_date", "") } returns "20261108"
        every { prefs.getBoolean("is_pro_user", false) } returns false
        every { prefs.getInt("free_scans", 3) } returns 3
        every { prefs.getInt("free_phrases", 5) } returns 5

        val manager = BillingManager(context)
        val state = manager.subscriptionState.value

        assertFalse(state.isProUser)
        assertEquals(3, state.freeDailyScansRemaining)
        assertEquals(5, state.freeDailyPhrasesRemaining)
    }

    @Test
    fun `consumeScanQuota decrements remaining scans for free tier`() {
        every { prefs.getString("last_date", "") } returns "20261108"
        every { prefs.getBoolean("is_pro_user", false) } returns false
        every { prefs.getInt("free_scans", 3) } returns 2

        val manager = BillingManager(context)
        val consumed = manager.consumeScanQuota()

        assertTrue(consumed)
        assertEquals(1, manager.subscriptionState.value.freeDailyScansRemaining)
        verify { editor.putInt("free_scans", 1) }
    }

    @Test
    fun `purchasePlan activates pro status and updates active tier`() {
        every { prefs.getString("last_date", "") } returns "20261108"
        every { prefs.getBoolean("is_pro_user", false) } returns false

        val manager = BillingManager(context)
        manager.purchasePlan(PlanTier.MONTHLY_PRO)

        assertTrue(manager.subscriptionState.value.isProUser)
        assertEquals(PlanTier.MONTHLY_PRO, manager.subscriptionState.value.activeTier)
        verify { editor.putBoolean("is_pro_user", true) }
        verify { editor.putString("pro_tier", PlanTier.MONTHLY_PRO.name) }
    }
}
