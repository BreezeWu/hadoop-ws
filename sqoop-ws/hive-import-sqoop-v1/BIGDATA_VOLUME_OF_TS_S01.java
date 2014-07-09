// ORM class for table 'BIGDATA_VOLUME_OF_TS_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Fri Jul 04 16:50:38 CST 2014
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

public class BIGDATA_VOLUME_OF_TS_S01 extends SqoopRecord  implements DBWritable, Writable {
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
  public BIGDATA_VOLUME_OF_TS_S01 with_CONS_NO(String CONS_NO) {
    this.CONS_NO = CONS_NO;
    return this;
  }
  private String YM;
  public String get_YM() {
    return YM;
  }
  public void set_YM(String YM) {
    this.YM = YM;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_YM(String YM) {
    this.YM = YM;
    return this;
  }
  private java.math.BigDecimal 尖峰电量;
  public java.math.BigDecimal get_尖峰电量() {
    return 尖峰电量;
  }
  public void set_尖峰电量(java.math.BigDecimal 尖峰电量) {
    this.尖峰电量 = 尖峰电量;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_尖峰电量(java.math.BigDecimal 尖峰电量) {
    this.尖峰电量 = 尖峰电量;
    return this;
  }
  private java.math.BigDecimal 尖峰电价;
  public java.math.BigDecimal get_尖峰电价() {
    return 尖峰电价;
  }
  public void set_尖峰电价(java.math.BigDecimal 尖峰电价) {
    this.尖峰电价 = 尖峰电价;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_尖峰电价(java.math.BigDecimal 尖峰电价) {
    this.尖峰电价 = 尖峰电价;
    return this;
  }
  private java.math.BigDecimal 峰电量;
  public java.math.BigDecimal get_峰电量() {
    return 峰电量;
  }
  public void set_峰电量(java.math.BigDecimal 峰电量) {
    this.峰电量 = 峰电量;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_峰电量(java.math.BigDecimal 峰电量) {
    this.峰电量 = 峰电量;
    return this;
  }
  private java.math.BigDecimal 峰电价;
  public java.math.BigDecimal get_峰电价() {
    return 峰电价;
  }
  public void set_峰电价(java.math.BigDecimal 峰电价) {
    this.峰电价 = 峰电价;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_峰电价(java.math.BigDecimal 峰电价) {
    this.峰电价 = 峰电价;
    return this;
  }
  private java.math.BigDecimal 平电量;
  public java.math.BigDecimal get_平电量() {
    return 平电量;
  }
  public void set_平电量(java.math.BigDecimal 平电量) {
    this.平电量 = 平电量;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_平电量(java.math.BigDecimal 平电量) {
    this.平电量 = 平电量;
    return this;
  }
  private java.math.BigDecimal 平电价;
  public java.math.BigDecimal get_平电价() {
    return 平电价;
  }
  public void set_平电价(java.math.BigDecimal 平电价) {
    this.平电价 = 平电价;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_平电价(java.math.BigDecimal 平电价) {
    this.平电价 = 平电价;
    return this;
  }
  private java.math.BigDecimal 谷电量;
  public java.math.BigDecimal get_谷电量() {
    return 谷电量;
  }
  public void set_谷电量(java.math.BigDecimal 谷电量) {
    this.谷电量 = 谷电量;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_谷电量(java.math.BigDecimal 谷电量) {
    this.谷电量 = 谷电量;
    return this;
  }
  private java.math.BigDecimal 谷电价;
  public java.math.BigDecimal get_谷电价() {
    return 谷电价;
  }
  public void set_谷电价(java.math.BigDecimal 谷电价) {
    this.谷电价 = 谷电价;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_谷电价(java.math.BigDecimal 谷电价) {
    this.谷电价 = 谷电价;
    return this;
  }
  private java.math.BigDecimal 脊骨电量;
  public java.math.BigDecimal get_脊骨电量() {
    return 脊骨电量;
  }
  public void set_脊骨电量(java.math.BigDecimal 脊骨电量) {
    this.脊骨电量 = 脊骨电量;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_脊骨电量(java.math.BigDecimal 脊骨电量) {
    this.脊骨电量 = 脊骨电量;
    return this;
  }
  private java.math.BigDecimal 脊骨电价;
  public java.math.BigDecimal get_脊骨电价() {
    return 脊骨电价;
  }
  public void set_脊骨电价(java.math.BigDecimal 脊骨电价) {
    this.脊骨电价 = 脊骨电价;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_脊骨电价(java.math.BigDecimal 脊骨电价) {
    this.脊骨电价 = 脊骨电价;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BIGDATA_VOLUME_OF_TS_S01)) {
      return false;
    }
    BIGDATA_VOLUME_OF_TS_S01 that = (BIGDATA_VOLUME_OF_TS_S01) o;
    boolean equal = true;
    equal = equal && (this.CONS_NO == null ? that.CONS_NO == null : this.CONS_NO.equals(that.CONS_NO));
    equal = equal && (this.YM == null ? that.YM == null : this.YM.equals(that.YM));
    equal = equal && (this.尖峰电量 == null ? that.尖峰电量 == null : this.尖峰电量.equals(that.尖峰电量));
    equal = equal && (this.尖峰电价 == null ? that.尖峰电价 == null : this.尖峰电价.equals(that.尖峰电价));
    equal = equal && (this.峰电量 == null ? that.峰电量 == null : this.峰电量.equals(that.峰电量));
    equal = equal && (this.峰电价 == null ? that.峰电价 == null : this.峰电价.equals(that.峰电价));
    equal = equal && (this.平电量 == null ? that.平电量 == null : this.平电量.equals(that.平电量));
    equal = equal && (this.平电价 == null ? that.平电价 == null : this.平电价.equals(that.平电价));
    equal = equal && (this.谷电量 == null ? that.谷电量 == null : this.谷电量.equals(that.谷电量));
    equal = equal && (this.谷电价 == null ? that.谷电价 == null : this.谷电价.equals(that.谷电价));
    equal = equal && (this.脊骨电量 == null ? that.脊骨电量 == null : this.脊骨电量.equals(that.脊骨电量));
    equal = equal && (this.脊骨电价 == null ? that.脊骨电价 == null : this.脊骨电价.equals(that.脊骨电价));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.CONS_NO = JdbcWritableBridge.readString(1, __dbResults);
    this.YM = JdbcWritableBridge.readString(2, __dbResults);
    this.尖峰电量 = JdbcWritableBridge.readBigDecimal(3, __dbResults);
    this.尖峰电价 = JdbcWritableBridge.readBigDecimal(4, __dbResults);
    this.峰电量 = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.峰电价 = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.平电量 = JdbcWritableBridge.readBigDecimal(7, __dbResults);
    this.平电价 = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.谷电量 = JdbcWritableBridge.readBigDecimal(9, __dbResults);
    this.谷电价 = JdbcWritableBridge.readBigDecimal(10, __dbResults);
    this.脊骨电量 = JdbcWritableBridge.readBigDecimal(11, __dbResults);
    this.脊骨电价 = JdbcWritableBridge.readBigDecimal(12, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(CONS_NO, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(YM, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(尖峰电量, 3 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(尖峰电价, 4 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(峰电量, 5 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(峰电价, 6 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(平电量, 7 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(平电价, 8 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(谷电量, 9 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(谷电价, 10 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(脊骨电量, 11 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(脊骨电价, 12 + __off, 2, __dbStmt);
    return 12;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.CONS_NO = null;
    } else {
    this.CONS_NO = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YM = null;
    } else {
    this.YM = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.尖峰电量 = null;
    } else {
    this.尖峰电量 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.尖峰电价 = null;
    } else {
    this.尖峰电价 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.峰电量 = null;
    } else {
    this.峰电量 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.峰电价 = null;
    } else {
    this.峰电价 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.平电量 = null;
    } else {
    this.平电量 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.平电价 = null;
    } else {
    this.平电价 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.谷电量 = null;
    } else {
    this.谷电量 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.谷电价 = null;
    } else {
    this.谷电价 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.脊骨电量 = null;
    } else {
    this.脊骨电量 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.脊骨电价 = null;
    } else {
    this.脊骨电价 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.CONS_NO) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, CONS_NO);
    }
    if (null == this.YM) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, YM);
    }
    if (null == this.尖峰电量) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.尖峰电量, __dataOut);
    }
    if (null == this.尖峰电价) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.尖峰电价, __dataOut);
    }
    if (null == this.峰电量) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.峰电量, __dataOut);
    }
    if (null == this.峰电价) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.峰电价, __dataOut);
    }
    if (null == this.平电量) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.平电量, __dataOut);
    }
    if (null == this.平电价) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.平电价, __dataOut);
    }
    if (null == this.谷电量) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.谷电量, __dataOut);
    }
    if (null == this.谷电价) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.谷电价, __dataOut);
    }
    if (null == this.脊骨电量) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.脊骨电量, __dataOut);
    }
    if (null == this.脊骨电价) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.脊骨电价, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(YM==null?"null":YM, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(尖峰电量==null?"null":尖峰电量.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(尖峰电价==null?"null":尖峰电价.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(峰电量==null?"null":峰电量.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(峰电价==null?"null":峰电价.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(平电量==null?"null":平电量.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(平电价==null?"null":平电价.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(谷电量==null?"null":谷电量.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(谷电价==null?"null":谷电价.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(脊骨电量==null?"null":脊骨电量.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(脊骨电价==null?"null":脊骨电价.toPlainString(), delimiters));
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
    if (__cur_str.equals("null")) { this.YM = null; } else {
      this.YM = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.尖峰电量 = null; } else {
      this.尖峰电量 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.尖峰电价 = null; } else {
      this.尖峰电价 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.峰电量 = null; } else {
      this.峰电量 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.峰电价 = null; } else {
      this.峰电价 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.平电量 = null; } else {
      this.平电量 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.平电价 = null; } else {
      this.平电价 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.谷电量 = null; } else {
      this.谷电量 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.谷电价 = null; } else {
      this.谷电价 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.脊骨电量 = null; } else {
      this.脊骨电量 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.脊骨电价 = null; } else {
      this.脊骨电价 = new java.math.BigDecimal(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_VOLUME_OF_TS_S01 o = (BIGDATA_VOLUME_OF_TS_S01) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("CONS_NO", this.CONS_NO);
    __sqoop$field_map.put("YM", this.YM);
    __sqoop$field_map.put("尖峰电量", this.尖峰电量);
    __sqoop$field_map.put("尖峰电价", this.尖峰电价);
    __sqoop$field_map.put("峰电量", this.峰电量);
    __sqoop$field_map.put("峰电价", this.峰电价);
    __sqoop$field_map.put("平电量", this.平电量);
    __sqoop$field_map.put("平电价", this.平电价);
    __sqoop$field_map.put("谷电量", this.谷电量);
    __sqoop$field_map.put("谷电价", this.谷电价);
    __sqoop$field_map.put("脊骨电量", this.脊骨电量);
    __sqoop$field_map.put("脊骨电价", this.脊骨电价);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("CONS_NO".equals(__fieldName)) {
      this.CONS_NO = (String) __fieldVal;
    }
    else    if ("YM".equals(__fieldName)) {
      this.YM = (String) __fieldVal;
    }
    else    if ("尖峰电量".equals(__fieldName)) {
      this.尖峰电量 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("尖峰电价".equals(__fieldName)) {
      this.尖峰电价 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("峰电量".equals(__fieldName)) {
      this.峰电量 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("峰电价".equals(__fieldName)) {
      this.峰电价 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("平电量".equals(__fieldName)) {
      this.平电量 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("平电价".equals(__fieldName)) {
      this.平电价 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("谷电量".equals(__fieldName)) {
      this.谷电量 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("谷电价".equals(__fieldName)) {
      this.谷电价 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("脊骨电量".equals(__fieldName)) {
      this.脊骨电量 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("脊骨电价".equals(__fieldName)) {
      this.脊骨电价 = (java.math.BigDecimal) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
