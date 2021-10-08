package jakarta.json.bind.tck.customizedmapping.instantiation.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbDefaultValue;
import jakarta.json.bind.annotation.JsonbProperty;

/**
 * TODO javadoc
 */
public class OptionalAllPrimitivesContainer {

    private final byte byteValue;
    private final short shortValue;
    private final int intValue;
    private final long longValue;
    private final float floatValue;
    private final double doubleValue;

    private final boolean booleanValue;
    private final char charValue;

    @JsonbCreator
    public OptionalAllPrimitivesContainer(@JsonbProperty("byteValue") @JsonbDefaultValue("1") byte byteValue,
                                          @JsonbProperty("shortValue") @JsonbDefaultValue("2") short shortValue,
                                          @JsonbProperty("intValue") @JsonbDefaultValue("3") int intValue,
                                          @JsonbProperty("longValue") @JsonbDefaultValue("4") long longValue,
                                          @JsonbProperty("floatValue") @JsonbDefaultValue("5") float floatValue,
                                          @JsonbProperty("doubleValue") @JsonbDefaultValue("6") double doubleValue,
                                          @JsonbProperty("booleanValue") @JsonbDefaultValue("true") boolean booleanValue,
                                          @JsonbProperty("shortValue") @JsonbDefaultValue("c") char charValue) {
        this.byteValue = byteValue;
        this.shortValue = shortValue;
        this.intValue = intValue;
        this.longValue = longValue;
        this.floatValue = floatValue;
        this.doubleValue = doubleValue;
        this.booleanValue = booleanValue;
        this.charValue = charValue;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public short getShortValue() {
        return shortValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    public char getCharValue() {
        return charValue;
    }
}
