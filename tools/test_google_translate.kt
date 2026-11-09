import org.json.JSONArray
fun main() {
    val responseStr = """[[["ಜಯನಗರ 4ನೇ ಬ್ಲಾಕ್ ಗೋ","Jayanagar 4th block go",null,null,3,null,null,[[]],[[["ee29150929b269c38979323546d85c49","tea_DravidianA_en2knmlsitate_2021q3.md"]]]]],null,"en",null,null,null,null,[]]"""
    val jsonArray = JSONArray(responseStr)
    val translatedText = jsonArray.getJSONArray(0).getJSONArray(0).getString(0)
    println(translatedText)
}
