// ORM class for table 'BIGDATA_ARC_VOLUME_PERM_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 15:48:36 CST 2014
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

public class BIGDATA_ARC_VOLUME_PERM_S01 extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private java.math.BigDecimal CONS_ID;
  public java.math.BigDecimal get_CONS_ID() {
    return CONS_ID;
  }
  public void set_CONS_ID(java.math.BigDecimal CONS_ID) {
    this.CONS_ID = CONS_ID;
  }
  public BIGDATA_ARC_VOLUME_PERM_S01 with_CONS_ID(java.math.BigDecimal CONS_ID) {
    this.CONS_ID = CONS_ID;
    return this;
  }
  private String 年月;
  public String get_年月() {
    return 年月;
  }
  public void set_年月(String 年月) {
    this.年月 = 年月;
  }
  public BIGDATA_ARC_VOLUME_PERM_S01 with_年月(String 年月) {
    this.年月 = 年月;
    return this;
  }
  private java.math.BigDecimal VOLUME_PER_MONTH;
  public java.math.BigDecimal get_VOLUME_PER_MONTH() {
    return VOLUME_PER_MONTH;
  }
  public void set_VOLUME_PER_MONTH(java.math.BigDecimal VOLUME_PER_MONTH) {
    this.VOLUME_PER_MONTH = VOLUME_PER_MONTH;
  }
  public BIGDATA_ARC_VOLUME_PERM_S01 with_VOLUME_PER_MONTH(java.math.BigDecimal VOLUME_PER_MONTH) {
    this.VOLUME_PER_MONTH = VOLUME_PER_MONTH;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BIGDATA_ARC_VOLUME_PERM_S01)) {
      return false;
    }
    BIGDATA_ARC_VOLUME_PERM_S01 that = (BIGDATA_ARC_VOLUME_PERM_S01) o;
    boolean equal = true;
    equal = equal && (this.CONS_ID == null ? that.CONS_ID == null : this.CONS_ID.equals(that.CONS_ID));
    equal = equal && (this.年月 == null ? that.年月 == null : this.年月.equals(that.年月));
    equal = equal && (this.VOLUME_PER_MONTH == null ? that.VOLUME_PER_MONTH == null : this.VOLUME_PER_MONTH.equals(that.VOLUME_PER_MONTH));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.CONS_ID = JdbcWritableBridge.readBigDecimal(1, __dbResults);
    this.年月 = JdbcWritableBridge.readString(2, __dbResults);
    this.VOLUME_PER_MONTH = JdbcWritableBridge.readBigDecimal(3, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeBigDecimal(CONS_ID, 1 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(年月, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(VOLUME_PER_MONTH, 3 + __off, 2, __dbStmt);
    return 3;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.CONS_ID = null;
    } else {
    this.CONS_ID = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.年月 = null;
    } else {
    this.年月 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.VOLUME_PER_MONTH = null;
    } else {
    this.VOLUME_PER_MONTH = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.CONS_ID) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.CONS_ID, __dataOut);
    }
    if (null == this.年月) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 年月);
    }
    if (null == this.VOLUME_PER_MONTH) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.VOLUME_PER_MONTH, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(CONS_ID==null?"null":CONS_ID.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(年月==null?"null":年月, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(VOLUME_PER_MONTH==null?"null":VOLUME_PER_MONTH.toPlainString(), delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.CONS_ID = null; } else {
      this.CONS_ID = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.年月 = null; } else {
      this.年月 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.VOLUME_PER_MONTH = null; } else {
      this.VOLUME_PER_MONTH = new java.math.BigDecimal(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_ARC_VOLUME_PERM_S01 o = (BIGDATA_ARC_VOLUME_PERM_S01) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("CONS_ID", this.CONS_ID);
    __sqoop$field_map.put("年月", this.年月);
    __sqoop$field_map.put("VOLUME_PER_MONTH", this.VOLUME_PER_MONTH);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("CONS_ID".equals(__fieldName)) {
      this.CONS_ID = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("年月".equals(__fieldName)) {
      this.年月 = (String) __fieldVal;
    }
    else    if ("VOLUME_PER_MONTH".equals(__fieldName)) {
      this.VOLUME_PER_MONTH = (java.math.BigDecimal) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
