/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.zumium.boxes.thrift;


public enum LinkType implements org.apache.thrift.TEnum {
  SOFT(0),
  HARD(1);

  private final int value;

  private LinkType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static LinkType findByValue(int value) { 
    switch (value) {
      case 0:
        return SOFT;
      case 1:
        return HARD;
      default:
        return null;
    }
  }
}