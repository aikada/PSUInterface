package ee.aikada.psuinterface.models.enums

enum class GraphType(val jsonName: String, val displayName: String, val unit: String) {
    Time("time", "Time", "s"),
    Voltage("voltage", "Voltage", "V"),
    Current("current", "Current", "A"),
    VoltageLimit("voltage limit", "Voltage limit", "(V)"),
    CurrentLimit("current limit", "Current limit", "(A)"),
    Resistance("resistance","Resistance", "Ω")
}
