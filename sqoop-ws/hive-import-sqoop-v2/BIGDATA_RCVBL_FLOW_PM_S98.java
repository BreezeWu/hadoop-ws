// ORM class for table 'BIGDATA_RCVBL_FLOW_PM_S98'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Tue Jul 08 15:38:06 CST 2014
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

public class BIGDATA_RCVBL_FLOW_PM_S98 extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private String 用户号;
  public String get_用户号() {
    return 用户号;
  }
  public void set_用户号(String 用户号) {
    this.用户号 = 用户号;
  }
  public BIGDATA_RCVBL_FLOW_PM_S98 with_用户号(String 用户号) {
    this.用户号 = 用户号;
    return this;
  }
  private String 年月;
  public String get_年月() {
    return 年月;
  }
  public void set_年月(String 年月) {
    this.年月 = 年月;
  }
  public BIGDATA_RCVBL_FLOW_PM_S98 with_年月(String 年月) {
    this.年月 = 年月;
    return this;
  }
  private java.math.BigDecimal 实收;
  public java.math.BigDecimal get_实收() {
    return 实收;
  }
  public void set_实收(java.math.BigDecimal 实收) {
    this.实收 = 实收;
  }
  public BIGDATA_RCVBL_FLOW_PM_S98 with_实收(java.math.BigDecimal 实收) {
    this.实收 = 实收;
    return this;
  }
  private java.math.BigDecimal 应收;
  public java.math.BigDecimal get_应收() {
    return 应收;
  }
  public void set_应收(java.math.BigDecimal 应收) {
    this.应收 = 应收;
  }
  public BIGDATA_RCVBL_FLOW_PM_S98 with_应收(java.math.BigDecimal 应收) {
    this.应收 = 应收;
    return this;
  }
  private java.math.BigDecimal 欠费;
  public java.math.BigDecimal get_欠费() {
    return 欠费;
  }
  public void set_欠费(java.math.BigDecimal 欠费) {
    this.欠费 = 欠费;
  }
  public BIGDATA_RCVBL_FLOW_PM_S98 with_欠费(java.math.BigDecimal 欠费) {
    this.欠费 = 欠费;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BIGDATA_RCVBL_FLOW_PM_S98)) {
      return false;
    }
    BIGDATA_RCVBL_FLOW_PM_S98 that = (BIGDATA_RCVBL_FLOW_PM_S98) o;
    boolean equal = true;
    equal = equal && (this.用户号 == null ? that.用户号 == null : this.用户号.equals(that.用户号));
    equal = equal && (this.年月 == null ? that.年月 == null : this.年月.equals(that.年月));
    equal = equal && (this.实收 == null ? that.实收 == null : this.实收.equals(that.实收));
    equal = equal && (this.应收 == null ? that.应收 == null : this.应收.equals(that.应收));
    equal = equal && (this.欠费 == null ? that.欠费 == null : this.欠费.equals(that.欠费));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.用户号 = JdbcWritableBridge.readString(1, __dbResults);
    this.年月 = JdbcWritableBridge.readString(2, __dbResults);
    this.实收 = JdbcWritableBridge.readBigDecimal(3, __dbResults);
    this.应收 = JdbcWritableBridge.readBigDecimal(4, __dbResults);
    this.欠费 = JdbcWritableBridge.readBigDecimal(5, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(用户号, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(年月, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(实收, 3 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(应收, 4 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(欠费, 5 + __off, 2, __dbStmt);
    return 5;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.用户号 = null;
    } else {
    this.用户号 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.年月 = null;
    } else {
    this.年月 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.实收 = null;
    } else {
    this.实收 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.应收 = null;
    } else {
    this.应收 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.欠费 = null;
    } else {
    this.欠费 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.用户号) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 用户号);
    }
    if (null == this.年月) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 年月);
    }
    if (null == this.实收) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.实收, __dataOut);
    }
    if (null == this.应收) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.应收, __dataOut);
    }
    if (null == this.欠费) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.欠费, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(用户号==null?"null":用户号, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(年月==null?"null":年月, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(实收==null?"null":实收.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(应收==null?"null":应收.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(欠费==null?"null":欠费.toPlainString(), delimiters));
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
    if (__cur_str.equals("null")) { this.用户号 = null; } else {
      this.用户号 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.年月 = null; } else {
      this.年月 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.实收 = null; } else {
      this.实收 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.应收 = null; } else {
      this.应收 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.欠费 = null; } else {
      this.欠费 = new java.math.BigDecimal(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_RCVBL_FLOW_PM_S98 o = (BIGDATA_RCVBL_FLOW_PM_S98) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("用户号", this.用户号);
    __sqoop$field_map.put("年月", this.年月);
    __sqoop$field_map.put("实收", this.实收);
    __sqoop$field_map.put("应收", this.应收);
    __sqoop$field_map.put("欠费", this.欠费);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("用户号".equals(__fieldName)) {
      this.用户号 = (String) __fieldVal;
    }
    else    if ("年月".equals(__fieldName)) {
      this.年月 = (String) __fieldVal;
    }
    else    if ("实收".equals(__fieldName)) {
      this.实收 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("应收".equals(__fieldName)) {
      this.应收 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("欠费".equals(__fieldName)) {
      this.欠费 = (java.math.BigDecimal) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
