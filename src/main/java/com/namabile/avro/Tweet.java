/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.namabile.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Tweet extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Tweet\",\"namespace\":\"com.namabile.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"text\",\"type\":\"string\"},{\"name\":\"created_at\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public long id;
  @Deprecated public java.lang.CharSequence name;
  @Deprecated public java.lang.CharSequence text;
  @Deprecated public long created_at;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Tweet() {}

  /**
   * All-args constructor.
   */
  public Tweet(java.lang.Long id, java.lang.CharSequence name, java.lang.CharSequence text, java.lang.Long created_at) {
    this.id = id;
    this.name = name;
    this.text = text;
    this.created_at = created_at;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return name;
    case 2: return text;
    case 3: return created_at;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: name = (java.lang.CharSequence)value$; break;
    case 2: text = (java.lang.CharSequence)value$; break;
    case 3: created_at = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'text' field.
   */
  public java.lang.CharSequence getText() {
    return text;
  }

  /**
   * Sets the value of the 'text' field.
   * @param value the value to set.
   */
  public void setText(java.lang.CharSequence value) {
    this.text = value;
  }

  /**
   * Gets the value of the 'created_at' field.
   */
  public java.lang.Long getCreatedAt() {
    return created_at;
  }

  /**
   * Sets the value of the 'created_at' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.lang.Long value) {
    this.created_at = value;
  }

  /** Creates a new Tweet RecordBuilder */
  public static com.namabile.avro.Tweet.Builder newBuilder() {
    return new com.namabile.avro.Tweet.Builder();
  }
  
  /** Creates a new Tweet RecordBuilder by copying an existing Builder */
  public static com.namabile.avro.Tweet.Builder newBuilder(com.namabile.avro.Tweet.Builder other) {
    return new com.namabile.avro.Tweet.Builder(other);
  }
  
  /** Creates a new Tweet RecordBuilder by copying an existing Tweet instance */
  public static com.namabile.avro.Tweet.Builder newBuilder(com.namabile.avro.Tweet other) {
    return new com.namabile.avro.Tweet.Builder(other);
  }
  
  /**
   * RecordBuilder for Tweet instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Tweet>
    implements org.apache.avro.data.RecordBuilder<Tweet> {

    private long id;
    private java.lang.CharSequence name;
    private java.lang.CharSequence text;
    private long created_at;

    /** Creates a new Builder */
    private Builder() {
      super(com.namabile.avro.Tweet.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.namabile.avro.Tweet.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.text)) {
        this.text = data().deepCopy(fields()[2].schema(), other.text);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.created_at)) {
        this.created_at = data().deepCopy(fields()[3].schema(), other.created_at);
        fieldSetFlags()[3] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Tweet instance */
    private Builder(com.namabile.avro.Tweet other) {
            super(com.namabile.avro.Tweet.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.text)) {
        this.text = data().deepCopy(fields()[2].schema(), other.text);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.created_at)) {
        this.created_at = data().deepCopy(fields()[3].schema(), other.created_at);
        fieldSetFlags()[3] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public java.lang.Long getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public com.namabile.avro.Tweet.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'id' field has been set */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'id' field */
    public com.namabile.avro.Tweet.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'name' field */
    public java.lang.CharSequence getName() {
      return name;
    }
    
    /** Sets the value of the 'name' field */
    public com.namabile.avro.Tweet.Builder setName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.name = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'name' field has been set */
    public boolean hasName() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'name' field */
    public com.namabile.avro.Tweet.Builder clearName() {
      name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'text' field */
    public java.lang.CharSequence getText() {
      return text;
    }
    
    /** Sets the value of the 'text' field */
    public com.namabile.avro.Tweet.Builder setText(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.text = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'text' field has been set */
    public boolean hasText() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'text' field */
    public com.namabile.avro.Tweet.Builder clearText() {
      text = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'created_at' field */
    public java.lang.Long getCreatedAt() {
      return created_at;
    }
    
    /** Sets the value of the 'created_at' field */
    public com.namabile.avro.Tweet.Builder setCreatedAt(long value) {
      validate(fields()[3], value);
      this.created_at = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'created_at' field has been set */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'created_at' field */
    public com.namabile.avro.Tweet.Builder clearCreatedAt() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public Tweet build() {
      try {
        Tweet record = new Tweet();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.name = fieldSetFlags()[1] ? this.name : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.text = fieldSetFlags()[2] ? this.text : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.created_at = fieldSetFlags()[3] ? this.created_at : (java.lang.Long) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
