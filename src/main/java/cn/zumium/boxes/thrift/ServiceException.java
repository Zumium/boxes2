/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.zumium.boxes.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-02-17")
public class ServiceException extends org.apache.thrift.TException implements org.apache.thrift.TBase<ServiceException, ServiceException._Fields>, java.io.Serializable, Cloneable, Comparable<ServiceException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ServiceException");

  private static final org.apache.thrift.protocol.TField OP_FIELD_DESC = new org.apache.thrift.protocol.TField("op", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField WHY_FIELD_DESC = new org.apache.thrift.protocol.TField("why", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ServiceExceptionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ServiceExceptionTupleSchemeFactory();

  public java.lang.String op; // required
  public java.lang.String why; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    OP((short)1, "op"),
    WHY((short)2, "why");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // OP
          return OP;
        case 2: // WHY
          return WHY;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.OP, new org.apache.thrift.meta_data.FieldMetaData("op", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.WHY, new org.apache.thrift.meta_data.FieldMetaData("why", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ServiceException.class, metaDataMap);
  }

  public ServiceException() {
  }

  public ServiceException(
    java.lang.String op,
    java.lang.String why)
  {
    this();
    this.op = op;
    this.why = why;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ServiceException(ServiceException other) {
    if (other.isSetOp()) {
      this.op = other.op;
    }
    if (other.isSetWhy()) {
      this.why = other.why;
    }
  }

  public ServiceException deepCopy() {
    return new ServiceException(this);
  }

  @Override
  public void clear() {
    this.op = null;
    this.why = null;
  }

  public java.lang.String getOp() {
    return this.op;
  }

  public ServiceException setOp(java.lang.String op) {
    this.op = op;
    return this;
  }

  public void unsetOp() {
    this.op = null;
  }

  /** Returns true if field op is set (has been assigned a value) and false otherwise */
  public boolean isSetOp() {
    return this.op != null;
  }

  public void setOpIsSet(boolean value) {
    if (!value) {
      this.op = null;
    }
  }

  public java.lang.String getWhy() {
    return this.why;
  }

  public ServiceException setWhy(java.lang.String why) {
    this.why = why;
    return this;
  }

  public void unsetWhy() {
    this.why = null;
  }

  /** Returns true if field why is set (has been assigned a value) and false otherwise */
  public boolean isSetWhy() {
    return this.why != null;
  }

  public void setWhyIsSet(boolean value) {
    if (!value) {
      this.why = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case OP:
      if (value == null) {
        unsetOp();
      } else {
        setOp((java.lang.String)value);
      }
      break;

    case WHY:
      if (value == null) {
        unsetWhy();
      } else {
        setWhy((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case OP:
      return getOp();

    case WHY:
      return getWhy();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case OP:
      return isSetOp();
    case WHY:
      return isSetWhy();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ServiceException)
      return this.equals((ServiceException)that);
    return false;
  }

  public boolean equals(ServiceException that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_op = true && this.isSetOp();
    boolean that_present_op = true && that.isSetOp();
    if (this_present_op || that_present_op) {
      if (!(this_present_op && that_present_op))
        return false;
      if (!this.op.equals(that.op))
        return false;
    }

    boolean this_present_why = true && this.isSetWhy();
    boolean that_present_why = true && that.isSetWhy();
    if (this_present_why || that_present_why) {
      if (!(this_present_why && that_present_why))
        return false;
      if (!this.why.equals(that.why))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetOp()) ? 131071 : 524287);
    if (isSetOp())
      hashCode = hashCode * 8191 + op.hashCode();

    hashCode = hashCode * 8191 + ((isSetWhy()) ? 131071 : 524287);
    if (isSetWhy())
      hashCode = hashCode * 8191 + why.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ServiceException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetOp()).compareTo(other.isSetOp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.op, other.op);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetWhy()).compareTo(other.isSetWhy());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWhy()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.why, other.why);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ServiceException(");
    boolean first = true;

    sb.append("op:");
    if (this.op == null) {
      sb.append("null");
    } else {
      sb.append(this.op);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("why:");
    if (this.why == null) {
      sb.append("null");
    } else {
      sb.append(this.why);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ServiceExceptionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ServiceExceptionStandardScheme getScheme() {
      return new ServiceExceptionStandardScheme();
    }
  }

  private static class ServiceExceptionStandardScheme extends org.apache.thrift.scheme.StandardScheme<ServiceException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ServiceException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // OP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.op = iprot.readString();
              struct.setOpIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // WHY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.why = iprot.readString();
              struct.setWhyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ServiceException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.op != null) {
        oprot.writeFieldBegin(OP_FIELD_DESC);
        oprot.writeString(struct.op);
        oprot.writeFieldEnd();
      }
      if (struct.why != null) {
        oprot.writeFieldBegin(WHY_FIELD_DESC);
        oprot.writeString(struct.why);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ServiceExceptionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ServiceExceptionTupleScheme getScheme() {
      return new ServiceExceptionTupleScheme();
    }
  }

  private static class ServiceExceptionTupleScheme extends org.apache.thrift.scheme.TupleScheme<ServiceException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ServiceException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetOp()) {
        optionals.set(0);
      }
      if (struct.isSetWhy()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetOp()) {
        oprot.writeString(struct.op);
      }
      if (struct.isSetWhy()) {
        oprot.writeString(struct.why);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ServiceException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.op = iprot.readString();
        struct.setOpIsSet(true);
      }
      if (incoming.get(1)) {
        struct.why = iprot.readString();
        struct.setWhyIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

