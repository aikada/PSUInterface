package ee.aikada.psuinterface.models.enums

enum class LimitType(val longName: String, val unitName: String) {
    C("Current", "A"),
    V("Voltage", "V")
}