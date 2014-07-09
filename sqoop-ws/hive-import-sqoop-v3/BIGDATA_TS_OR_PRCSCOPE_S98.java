// ORM class for table 'BIGDATA_TS_OR_PRCSCOPE_S98'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 14:21:12 CST 2014
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

public class BIGDATA_TS_OR_PRCSCOPE_S98 extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private String CONS_NO;
  public String get_CONS_NO() {
    return CONS_NO;
  }
  public void set_CONS_NO(String CONS_NO) {
    this.CONS_NO = CONS_NO;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S98 with_CONS_NO(String CONS_NO) {
    this.CONS_NO = CONS_NO;
    return this;
  }
  private String PRC_CODE;
  public String get_PRC_CODE() {
    return PRC_CODE;
  }
  public void set_PRC_CODE(String PRC_CODE) {
    this.PRC_CODE = PRC_CODE;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S98 with_PRC_CODE(String PRC_CODE) {
    this.PRC_CODE = PRC_CODE;
    return this;
  }
  private String TS_FLAG;
  public String get_TS_FLAG() {
    return TS_FLAG;
  }
  public void set_TS_FLAG(String TS_FLAG) {
    this.TS_FLAG = TS_FLAG;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S98 with_TS_FLAG(String TS_FLAG) {
    this.TS_FLAG = TS_FLAG;
    return this;
  }
  private String LADDER_FLAG;
  public String get_LADDER_FLAG() {
    return LADDER_FLAG;
  }
  public void set_LADDER_FLAG(String LADDER_FLAG) {
    this.LADDER_FLAG = LADDER_FLAG;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S98 with_LADDER_FLAG(String LADDER_FLAG) {
    this.LADDER_FLAG = LADDER_FLAG;
    return this;
  }
  private String ORG_NO;
  public String get_ORG_NO() {
    return ORG_NO;
  }
  public void set_ORG_NO(String ORG_NO) {
    this.ORG_NO = ORG_NO;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S98 with_ORG_NO(String ORG_NO) {
    this.ORG_NO = ORG_NO;
    return this;
  }
  private java.math.BigDecimal TS_FLAG_I;
  public java.math.BigDecimal get_TS_FLAG_I() {
    return TS_FLAG_I;
  }
  public void set_TS_FLAG_I(java.math.BigDecimal TS_FLAG_I) {
    this.TS_FLAG_I = TS_FLAG_I;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S98 with_TS_FLAG_I(java.math.BigDecimal TS_FLAG_I) {
    this.TS_FLAG_I = TS_FLAG_I;
    return this;
  }
  private java.math.BigDecimal LADDER_FLAG_I;
  public java.math.BigDecimal get_LADDER_FLAG_I() {
    return LADDER_FLAG_I;
  }
  public void set_LADDER_FLAG_I(java.math.BigDecimal LADDER_FLAG_I) {
    this.LADDER_FLAG_I = LADDER_FLAG_I;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S98 with_LADDER_FLAG_I(java.math.BigDecimal LADDER_FLAG_I) {
    this.LADDER_FLAG_I = LADDER_FLAG_I;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BIGDATA_TS_OR_PRCSCOPE_S98)) {
      return false;
    }
    BIGDATA_TS_OR_PRCSCOPE_S98 that = (BIGDATA_TS_OR_PRCSCOPE_S98) o;
    boolean equal = true;
    equal = equal && (this.CONS_NO == null ? that.CONS_NO == null : this.CONS_NO.equals(that.CONS_NO));
    equal = equal && (this.PRC_CODE == null ? that.PRC_CODE == null : this.PRC_CODE.equals(that.PRC_CODE));
    equal = equal && (this.TS_FLAG == null ? that.TS_FLAG == null : this.TS_FLAG.equals(that.TS_FLAG));
    equal = equal && (this.LADDER_FLAG == null ? that.LADDER_FLAG == null : this.LADDER_FLAG.equals(that.LADDER_FLAG));
    equal = equal && (this.ORG_NO == null ? that.ORG_NO == null : this.ORG_NO.equals(that.ORG_NO));
    equal = equal && (this.TS_FLAG_I == null ? that.TS_FLAG_I == null : this.TS_FLAG_I.equals(that.TS_FLAG_I));
    equal = equal && (this.LADDER_FLAG_I == null ? that.LADDER_FLAG_I == null : this.LADDER_FLAG_I.equals(that.LADDER_FLAG_I));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.CONS_NO = JdbcWritableBridge.readString(1, __dbResults);
    this.PRC_CODE = JdbcWritableBridge.readString(2, __dbResults);
    this.TS_FLAG = JdbcWritableBridge.readString(3, __dbResults);
    this.LADDER_FLAG = JdbcWritableBridge.readString(4, __dbResults);
    this.ORG_NO = JdbcWritableBridge.readString(5, __dbResults);
    this.TS_FLAG_I = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.LADDER_FLAG_I = JdbcWritableBridge.readBigDecimal(7, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(CONS_NO, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(PRC_CODE, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(TS_FLAG, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(LADDER_FLAG, 4 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(ORG_NO, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(TS_FLAG_I, 6 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(LADDER_FLAG_I, 7 + __off, 2, __dbStmt);
    return 7;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.CONS_NO = null;
    } else {
    this.CONS_NO = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.PRC_CODE = null;
    } else {
    this.PRC_CODE = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.TS_FLAG = null;
    } else {
    this.TS_FLAG = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.LADDER_FLAG = null;
    } else {
    this.LADDER_FLAG = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ORG_NO = null;
    } else {
    this.ORG_NO = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.TS_FLAG_I = null;
    } else {
    this.TS_FLAG_I = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.LADDER_FLAG_I = null;
    } else {
    this.LADDER_FLAG_I = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.CONS_NO) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, CONS_NO);
    }
    if (null == this.PRC_CODE) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, PRC_CODE);
    }
    if (null == this.TS_FLAG) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, TS_FLAG);
    }
    if (null == this.LADDER_FLAG) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, LADDER_FLAG);
    }
    if (null == this.ORG_NO) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ORG_NO);
    }
    if (null == this.TS_FLAG_I) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.TS_FLAG_I, __dataOut);
    }
    if (null == this.LADDER_FLAG_I) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.LADDER_FLAG_I, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(CONS_NO==null?"null":CONS_NO, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(PRC_CODE==null?"null":PRC_CODE, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(TS_FLAG==null?"null":TS_FLAG, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(LADDER_FLAG==null?"null":LADDER_FLAG, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ORG_NO==null?"null":ORG_NO, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(TS_FLAG_I==null?"null":TS_FLAG_I.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(LADDER_FLAG_I==null?"null":LADDER_FLAG_I.toPlainString(), delimiters));
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
    if (__cur_str.equals("null")) { this.CONS_NO = null; } else {
      this.CONS_NO = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.PRC_CODE = null; } else {
      this.PRC_CODE = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.TS_FLAG = null; } else {
      this.TS_FLAG = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.LADDER_FLAG = null; } else {
      this.LADDER_FLAG = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ORG_NO = null; } else {
      this.ORG_NO = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.TS_FLAG_I = null; } else {
      this.TS_FLAG_I = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.LADDER_FLAG_I = null; } else {
      this.LADDER_FLAG_I = new java.math.BigDecimal(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_TS_OR_PRCSCOPE_S98 o = (BIGDATA_TS_OR_PRCSCOPE_S98) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("CONS_NO", this.CONS_NO);
    __sqoop$field_map.put("PRC_CODE", this.PRC_CODE);
    __sqoop$field_map.put("TS_FLAG", this.TS_FLAG);
    __sqoop$field_map.put("LADDER_FLAG", this.LADDER_FLAG);
    __sqoop$field_map.put("ORG_NO", this.ORG_NO);
    __sqoop$field_map.put("TS_FLAG_I", this.TS_FLAG_I);
    __sqoop$field_map.put("LADDER_FLAG_I", this.LADDER_FLAG_I);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("CONS_NO".equals(__fieldName)) {
      this.CONS_NO = (String) __fieldVal;
    }
    else    if ("PRC_CODE".equals(__fieldName)) {
      this.PRC_CODE = (String) __fieldVal;
    }
    else    if ("TS_FLAG".equals(__fieldName)) {
      this.TS_FLAG = (String) __fieldVal;
    }
    else    if ("LADDER_FLAG".equals(__fieldName)) {
      this.LADDER_FLAG = (String) __fieldVal;
    }
    else    if ("ORG_NO".equals(__fieldName)) {
      this.ORG_NO = (String) __fieldVal;
    }
    else    if ("TS_FLAG_I".equals(__fieldName)) {
      this.TS_FLAG_I = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("LADDER_FLAG_I".equals(__fieldName)) {
      this.LADDER_FLAG_I = (java.math.BigDecimal) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
