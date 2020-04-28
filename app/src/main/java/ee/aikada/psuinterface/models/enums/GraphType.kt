package ee.aikada.psuinterface.models.enums

enum class GraphType(val longName: String) {
    Time("time"),
    Voltage("voltage"),
    Current("current"),
    VoltageLimit("voltage limit"),
    CurrentLimit("current limit"),
    Resistance("resistance")
}
