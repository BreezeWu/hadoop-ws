// ORM class for table 'BIGDATA_ARC_VOLUME_PERM_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Fri Jul 04 16:45:41 CST 2014
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
  private java.math.BigDecimal 用户ID;
  public java.math.BigDecimal get_用户ID() {
    return 用户ID;
  }
  public void set_用户ID(java.math.BigDecimal 用户ID) {
    this.用户ID = 用户ID;
  }
  public BIGDATA_ARC_VOLUME_PERM_S01 with_用户ID(java.math.BigDecimal 用户ID) {
    this.用户ID = 用户ID;
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
  private java.math.BigDecimal 月用电量;
  public java.math.BigDecimal get_月用电量() {
    return 月用电量;
  }
  public void set_月用电量(java.math.BigDecimal 月用电量) {
    this.月用电量 = 月用电量;
  }
  public BIGDATA_ARC_VOLUME_PERM_S01 with_月用电量(java.math.BigDecimal 月用电量) {
    this.月用电量 = 月用电量;
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
    equal = equal && (this.用户ID == null ? that.用户ID == null : this.用户ID.equals(that.用户ID));
    equal = equal && (this.年月 == null ? that.年月 == null : this.年月.equals(that.年月));
    equal = equal && (this.月用电量 == null ? that.月用电量 == null : this.月用电量.equals(that.月用电量));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.用户ID = JdbcWritableBridge.readBigDecimal(1, __dbResults);
    this.年月 = JdbcWritableBridge.readString(2, __dbResults);
    this.月用电量 = JdbcWritableBridge.readBigDecimal(3, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeBigDecimal(用户ID, 1 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(年月, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(月用电量, 3 + __off, 2, __dbStmt);
    return 3;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.用户ID = null;
    } else {
    this.用户ID = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.年月 = null;
    } else {
    this.年月 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.月用电量 = null;
    } else {
    this.月用电量 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.用户ID) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.用户ID, __dataOut);
    }
    if (null == this.年月) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 年月);
    }
    if (null == this.月用电量) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.月用电量, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(年月==null?"null":年月, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(月用电量==null?"null":月用电量.toPlainString(), delimiters));
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
    if (__cur_str.equals("null")) { this.年月 = null; } else {
      this.年月 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.月用电量 = null; } else {
      this.月用电量 = new java.math.BigDecimal(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_ARC_VOLUME_PERM_S01 o = (BIGDATA_ARC_VOLUME_PERM_S01) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("用户ID", this.用户ID);
    __sqoop$field_map.put("年月", this.年月);
    __sqoop$field_map.put("月用电量", this.月用电量);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("用户ID".equals(__fieldName)) {
      this.用户ID = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("年月".equals(__fieldName)) {
      this.年月 = (String) __fieldVal;
    }
    else    if ("月用电量".equals(__fieldName)) {
      this.月用电量 = (java.math.BigDecimal) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
