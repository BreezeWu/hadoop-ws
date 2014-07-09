// ORM class for table 'EPSA_LN.SA_PROP_LIST'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Tue Jul 08 15:20:36 CST 2014
// For connector: org.apache.sqoop.manager.OracleManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EPSA_LN_SA_PROP_LIST extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private String PROP_TYPE_ID;
  public String get_PROP_TYPE_ID() {
    return PROP_TYPE_ID;
  }
  public void set_PROP_TYPE_ID(String PROP_TYPE_ID) {
    this.PROP_TYPE_ID = PROP_TYPE_ID;
  }
  public EPSA_LN_SA_PROP_LIST with_PROP_TYPE_ID(String PROP_TYPE_ID) {
    this.PROP_TYPE_ID = PROP_TYPE_ID;
    return this;
  }
  private String PROP_LIST_ID;
  public String get_PROP_LIST_ID() {
    return PROP_LIST_ID;
  }
  public void set_PROP_LIST_ID(String PROP_LIST_ID) {
    this.PROP_LIST_ID = PROP_LIST_ID;
  }
  public EPSA_LN_SA_PROP_LIST with_PROP_LIST_ID(String PROP_LIST_ID) {
    this.PROP_LIST_ID = PROP_LIST_ID;
    return this;
  }
  private String PROP_LIST_NAME;
  public String get_PROP_LIST_NAME() {
    return PROP_LIST_NAME;
  }
  public void set_PROP_LIST_NAME(String PROP_LIST_NAME) {
    this.PROP_LIST_NAME = PROP_LIST_NAME;
  }
  public EPSA_LN_SA_PROP_LIST with_PROP_LIST_NAME(String PROP_LIST_NAME) {
    this.PROP_LIST_NAME = PROP_LIST_NAME;
    return this;
  }
  private String LINK_LIST_ID;
  public String get_LINK_LIST_ID() {
    return LINK_LIST_ID;
  }
  public void set_LINK_LIST_ID(String LINK_LIST_ID) {
    this.LINK_LIST_ID = LINK_LIST_ID;
  }
  public EPSA_LN_SA_PROP_LIST with_LINK_LIST_ID(String LINK_LIST_ID) {
    this.LINK_LIST_ID = LINK_LIST_ID;
    return this;
  }
  private String IS_DELETE;
  public String get_IS_DELETE() {
    return IS_DELETE;
  }
  public void set_IS_DELETE(String IS_DELETE) {
    this.IS_DELETE = IS_DELETE;
  }
  public EPSA_LN_SA_PROP_LIST with_IS_DELETE(String IS_DELETE) {
    this.IS_DELETE = IS_DELETE;
    return this;
  }
  private String IS_RESERVED;
  public String get_IS_RESERVED() {
    return IS_RESERVED;
  }
  public void set_IS_RESERVED(String IS_RESERVED) {
    this.IS_RESERVED = IS_RESERVED;
  }
  public EPSA_LN_SA_PROP_LIST with_IS_RESERVED(String IS_RESERVED) {
    this.IS_RESERVED = IS_RESERVED;
    return this;
  }
  private String PROP_LIST_MEMO;
  public String get_PROP_LIST_MEMO() {
    return PROP_LIST_MEMO;
  }
  public void set_PROP_LIST_MEMO(String PROP_LIST_MEMO) {
    this.PROP_LIST_MEMO = PROP_LIST_MEMO;
  }
  public EPSA_LN_SA_PROP_LIST with_PROP_LIST_MEMO(String PROP_LIST_MEMO) {
    this.PROP_LIST_MEMO = PROP_LIST_MEMO;
    return this;
  }
  private java.math.BigDecimal ORDER_ID;
  public java.math.BigDecimal get_ORDER_ID() {
    return ORDER_ID;
  }
  public void set_ORDER_ID(java.math.BigDecimal ORDER_ID) {
    this.ORDER_ID = ORDER_ID;
  }
  public EPSA_LN_SA_PROP_LIST with_ORDER_ID(java.math.BigDecimal ORDER_ID) {
    this.ORDER_ID = ORDER_ID;
    return this;
  }
  private String PROP_LIST_LABEL;
  public String get_PROP_LIST_LABEL() {
    return PROP_LIST_LABEL;
  }
  public void set_PROP_LIST_LABEL(String PROP_LIST_LABEL) {
    this.PROP_LIST_LABEL = PROP_LIST_LABEL;
  }
  public EPSA_LN_SA_PROP_LIST with_PROP_LIST_LABEL(String PROP_LIST_LABEL) {
    this.PROP_LIST_LABEL = PROP_LIST_LABEL;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EPSA_LN_SA_PROP_LIST)) {
      return false;
    }
    EPSA_LN_SA_PROP_LIST that = (EPSA_LN_SA_PROP_LIST) o;
    boolean equal = true;
    equal = equal && (this.PROP_TYPE_ID == null ? that.PROP_TYPE_ID == null : this.PROP_TYPE_ID.equals(that.PROP_TYPE_ID));
    equal = equal && (this.PROP_LIST_ID == null ? that.PROP_LIST_ID == null : this.PROP_LIST_ID.equals(that.PROP_LIST_ID));
    equal = equal && (this.PROP_LIST_NAME == null ? that.PROP_LIST_NAME == null : this.PROP_LIST_NAME.equals(that.PROP_LIST_NAME));
    equal = equal && (this.LINK_LIST_ID == null ? that.LINK_LIST_ID == null : this.LINK_LIST_ID.equals(that.LINK_LIST_ID));
    equal = equal && (this.IS_DELETE == null ? that.IS_DELETE == null : this.IS_DELETE.equals(that.IS_DELETE));
    equal = equal && (this.IS_RESERVED == null ? that.IS_RESERVED == null : this.IS_RESERVED.equals(that.IS_RESERVED));
    equal = equal && (this.PROP_LIST_MEMO == null ? that.PROP_LIST_MEMO == null : this.PROP_LIST_MEMO.equals(that.PROP_LIST_MEMO));
    equal = equal && (this.ORDER_ID == null ? that.ORDER_ID == null : this.ORDER_ID.equals(that.ORDER_ID));
    equal = equal && (this.PROP_LIST_LABEL == null ? that.PROP_LIST_LABEL == null : this.PROP_LIST_LABEL.equals(that.PROP_LIST_LABEL));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.PROP_TYPE_ID = JdbcWritableBridge.readString(1, __dbResults);
    this.PROP_LIST_ID = JdbcWritableBridge.readString(2, __dbResults);
    this.PROP_LIST_NAME = JdbcWritableBridge.readString(3, __dbResults);
    this.LINK_LIST_ID = JdbcWritableBridge.readString(4, __dbResults);
    this.IS_DELETE = JdbcWritableBridge.readString(5, __dbResults);
    this.IS_RESERVED = JdbcWritableBridge.readString(6, __dbResults);
    this.PROP_LIST_MEMO = JdbcWritableBridge.readString(7, __dbResults);
    this.ORDER_ID = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.PROP_LIST_LABEL = JdbcWritableBridge.readString(9, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(PROP_TYPE_ID, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(PROP_LIST_ID, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(PROP_LIST_NAME, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(LINK_LIST_ID, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(IS_DELETE, 5 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(IS_RESERVED, 6 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(PROP_LIST_MEMO, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(ORDER_ID, 8 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(PROP_LIST_LABEL, 9 + __off, 12, __dbStmt);
    return 9;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.PROP_TYPE_ID = null;
    } else {
    this.PROP_TYPE_ID = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.PROP_LIST_ID = null;
    } else {
    this.PROP_LIST_ID = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.PROP_LIST_NAME = null;
    } else {
    this.PROP_LIST_NAME = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.LINK_LIST_ID = null;
    } else {
    this.LINK_LIST_ID = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.IS_DELETE = null;
    } else {
    this.IS_DELETE = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.IS_RESERVED = null;
    } else {
    this.IS_RESERVED = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.PROP_LIST_MEMO = null;
    } else {
    this.PROP_LIST_MEMO = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ORDER_ID = null;
    } else {
    this.ORDER_ID = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.PROP_LIST_LABEL = null;
    } else {
    this.PROP_LIST_LABEL = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.PROP_TYPE_ID) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, PROP_TYPE_ID);
    }
    if (null == this.PROP_LIST_ID) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, PROP_LIST_ID);
    }
    if (null == this.PROP_LIST_NAME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, PROP_LIST_NAME);
    }
    if (null == this.LINK_LIST_ID) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, LINK_LIST_ID);
    }
    if (null == this.IS_DELETE) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, IS_DELETE);
    }
    if (null == this.IS_RESERVED) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, IS_RESERVED);
    }
    if (null == this.PROP_LIST_MEMO) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, PROP_LIST_MEMO);
    }
    if (null == this.ORDER_ID) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.ORDER_ID, __dataOut);
    }
    if (null == this.PROP_LIST_LABEL) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, PROP_LIST_LABEL);
    }
  }
  private final DelimiterSet __outputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(PROP_TYPE_ID==null?"null":PROP_TYPE_ID, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(PROP_LIST_ID==null?"null":PROP_LIST_ID, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(PROP_LIST_NAME==null?"null":PROP_LIST_NAME, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(LINK_LIST_ID==null?"null":LINK_LIST_ID, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(IS_DELETE==null?"null":IS_DELETE, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(IS_RESERVED==null?"null":IS_RESERVED, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(PROP_LIST_MEMO==null?"null":PROP_LIST_MEMO, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ORDER_ID==null?"null":ORDER_ID.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(PROP_LIST_LABEL==null?"null":PROP_LIST_LABEL, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  private final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str;
    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.PROP_TYPE_ID = null; } else {
      this.PROP_TYPE_ID = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.PROP_LIST_ID = null; } else {
      this.PROP_LIST_ID = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.PROP_LIST_NAME = null; } else {
      this.PROP_LIST_NAME = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.LINK_LIST_ID = null; } else {
      this.LINK_LIST_ID = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.IS_DELETE = null; } else {
      this.IS_DELETE = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.IS_RESERVED = null; } else {
      this.IS_RESERVED = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.PROP_LIST_MEMO = null; } else {
      this.PROP_LIST_MEMO = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ORDER_ID = null; } else {
      this.ORDER_ID = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.PROP_LIST_LABEL = null; } else {
      this.PROP_LIST_LABEL = __cur_str;
    }

  }

  public Object clone() throws CloneNotSupportedException {
    EPSA_LN_SA_PROP_LIST o = (EPSA_LN_SA_PROP_LIST) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("PROP_TYPE_ID", this.PROP_TYPE_ID);
    __sqoop$field_map.put("PROP_LIST_ID", this.PROP_LIST_ID);
    __sqoop$field_map.put("PROP_LIST_NAME", this.PROP_LIST_NAME);
    __sqoop$field_map.put("LINK_LIST_ID", this.LINK_LIST_ID);
    __sqoop$field_map.put("IS_DELETE", this.IS_DELETE);
    __sqoop$field_map.put("IS_RESERVED", this.IS_RESERVED);
    __sqoop$field_map.put("PROP_LIST_MEMO", this.PROP_LIST_MEMO);
    __sqoop$field_map.put("ORDER_ID", this.ORDER_ID);
    __sqoop$field_map.put("PROP_LIST_LABEL", this.PROP_LIST_LABEL);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("PROP_TYPE_ID".equals(__fieldName)) {
      this.PROP_TYPE_ID = (String) __fieldVal;
    }
    else    if ("PROP_LIST_ID".equals(__fieldName)) {
      this.PROP_LIST_ID = (String) __fieldVal;
    }
    else    if ("PROP_LIST_NAME".equals(__fieldName)) {
      this.PROP_LIST_NAME = (String) __fieldVal;
    }
    else    if ("LINK_LIST_ID".equals(__fieldName)) {
      this.LINK_LIST_ID = (String) __fieldVal;
    }
    else    if ("IS_DELETE".equals(__fieldName)) {
      this.IS_DELETE = (String) __fieldVal;
    }
    else    if ("IS_RESERVED".equals(__fieldName)) {
      this.IS_RESERVED = (String) __fieldVal;
    }
    else    if ("PROP_LIST_MEMO".equals(__fieldName)) {
      this.PROP_LIST_MEMO = (String) __fieldVal;
    }
    else    if ("ORDER_ID".equals(__fieldName)) {
      this.ORDER_ID = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("PROP_LIST_LABEL".equals(__fieldName)) {
      this.PROP_LIST_LABEL = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
