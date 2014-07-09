// ORM class for table 'BIGDATA_CHANGE_PAYTYPE_PY_S98'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Tue Jul 08 14:30:30 CST 2014
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

public class BIGDATA_CHANGE_PAYTYPE_PY_S98 extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private java.math.BigDecimal 用户ID;
  public java.math.BigDecimal get_用户ID() {
    return 用户ID;
  }
  public void set_用户ID(java.math.BigDecimal 用户ID) {
    this.用户ID = 用户ID;
  }
  public BIGDATA_CHANGE_PAYTYPE_PY_S98 with_用户ID(java.math.BigDecimal 用户ID) {
    this.用户ID = 用户ID;
    return this;
  }
  private String 年份;
  public String get_年份() {
    return 年份;
  }
  public void set_年份(String 年份) {
    this.年份 = 年份;
  }
  public BIGDATA_CHANGE_PAYTYPE_PY_S98 with_年份(String 年份) {
    this.年份 = 年份;
    return this;
  }
  private java.math.BigDecimal 缴费方式变更次数;
  public java.math.BigDecimal get_缴费方式变更次数() {
    return 缴费方式变更次数;
  }
  public void set_缴费方式变更次数(java.math.BigDecimal 缴费方式变更次数) {
    this.缴费方式变更次数 = 缴费方式变更次数;
  }
  public BIGDATA_CHANGE_PAYTYPE_PY_S98 with_缴费方式变更次数(java.math.BigDecimal 缴费方式变更次数) {
    this.缴费方式变更次数 = 缴费方式变更次数;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BIGDATA_CHANGE_PAYTYPE_PY_S98)) {
      return false;
    }
    BIGDATA_CHANGE_PAYTYPE_PY_S98 that = (BIGDATA_CHANGE_PAYTYPE_PY_S98) o;
    boolean equal = true;
    equal = equal && (this.用户ID == null ? that.用户ID == null : this.用户ID.equals(that.用户ID));
    equal = equal && (this.年份 == null ? that.年份 == null : this.年份.equals(that.年份));
    equal = equal && (this.缴费方式变更次数 == null ? that.缴费方式变更次数 == null : this.缴费方式变更次数.equals(that.缴费方式变更次数));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.用户ID = JdbcWritableBridge.readBigDecimal(1, __dbResults);
    this.年份 = JdbcWritableBridge.readString(2, __dbResults);
    this.缴费方式变更次数 = JdbcWritableBridge.readBigDecimal(3, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeBigDecimal(用户ID, 1 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(年份, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(缴费方式变更次数, 3 + __off, 2, __dbStmt);
    return 3;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.用户ID = null;
    } else {
    this.用户ID = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.年份 = null;
    } else {
    this.年份 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.缴费方式变更次数 = null;
    } else {
    this.缴费方式变更次数 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.用户ID) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.用户ID, __dataOut);
    }
    if (null == this.年份) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 年份);
    }
    if (null == this.缴费方式变更次数) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.缴费方式变更次数, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(用户ID==null?"null":用户ID.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(年份==null?"null":年份, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(缴费方式变更次数==null?"null":缴费方式变更次数.toPlainString(), delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.用户ID = null; } else {
      this.用户ID = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.年份 = null; } else {
      this.年份 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.缴费方式变更次数 = null; } else {
      this.缴费方式变更次数 = new java.math.BigDecimal(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_CHANGE_PAYTYPE_PY_S98 o = (BIGDATA_CHANGE_PAYTYPE_PY_S98) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("用户ID", this.用户ID);
    __sqoop$field_map.put("年份", this.年份);
    __sqoop$field_map.put("缴费方式变更次数", this.缴费方式变更次数);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("用户ID".equals(__fieldName)) {
      this.用户ID = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("年份".equals(__fieldName)) {
      this.年份 = (String) __fieldVal;
    }
    else    if ("缴费方式变更次数".equals(__fieldName)) {
      this.缴费方式变更次数 = (java.math.BigDecimal) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
