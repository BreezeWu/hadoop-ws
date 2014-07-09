// ORM class for table 'BIGDATA_RCVBL_FLOW_PM_S98'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 11:24:10 CST 2014
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
  private String CONS_NO;
  public String get_CONS_NO() {
    return CONS_NO;
  }
  public void set_CONS_NO(String CONS_NO) {
    this.CONS_NO = CONS_NO;
  }
  public BIGDATA_RCVBL_FLOW_PM_S98 with_CONS_NO(String CONS_NO) {
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
  public BIGDATA_RCVBL_FLOW_PM_S98 with_YM(String YM) {
    this.YM = YM;
    return this;
  }
  private java.math.BigDecimal RCVED_AMT;
  public java.math.BigDecimal get_RCVED_AMT() {
    return RCVED_AMT;
  }
  public void set_RCVED_AMT(java.math.BigDecimal RCVED_AMT) {
    this.RCVED_AMT = RCVED_AMT;
  }
  public BIGDATA_RCVBL_FLOW_PM_S98 with_RCVED_AMT(java.math.BigDecimal RCVED_AMT) {
    this.RCVED_AMT = RCVED_AMT;
    return this;
  }
  private java.math.BigDecimal RCVBL_AMT;
  public java.math.BigDecimal get_RCVBL_AMT() {
    return RCVBL_AMT;
  }
  public void set_RCVBL_AMT(java.math.BigDecimal RCVBL_AMT) {
    this.RCVBL_AMT = RCVBL_AMT;
  }
  public BIGDATA_RCVBL_FLOW_PM_S98 with_RCVBL_AMT(java.math.BigDecimal RCVBL_AMT) {
    this.RCVBL_AMT = RCVBL_AMT;
    return this;
  }
  private java.math.BigDecimal OWNING_AMT;
  public java.math.BigDecimal get_OWNING_AMT() {
    return OWNING_AMT;
  }
  public void set_OWNING_AMT(java.math.BigDecimal OWNING_AMT) {
    this.OWNING_AMT = OWNING_AMT;
  }
  public BIGDATA_RCVBL_FLOW_PM_S98 with_OWNING_AMT(java.math.BigDecimal OWNING_AMT) {
    this.OWNING_AMT = OWNING_AMT;
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
    equal = equal && (this.CONS_NO == null ? that.CONS_NO == null : this.CONS_NO.equals(that.CONS_NO));
    equal = equal && (this.YM == null ? that.YM == null : this.YM.equals(that.YM));
    equal = equal && (this.RCVED_AMT == null ? that.RCVED_AMT == null : this.RCVED_AMT.equals(that.RCVED_AMT));
    equal = equal && (this.RCVBL_AMT == null ? that.RCVBL_AMT == null : this.RCVBL_AMT.equals(that.RCVBL_AMT));
    equal = equal && (this.OWNING_AMT == null ? that.OWNING_AMT == null : this.OWNING_AMT.equals(that.OWNING_AMT));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.CONS_NO = JdbcWritableBridge.readString(1, __dbResults);
    this.YM = JdbcWritableBridge.readString(2, __dbResults);
    this.RCVED_AMT = JdbcWritableBridge.readBigDecimal(3, __dbResults);
    this.RCVBL_AMT = JdbcWritableBridge.readBigDecimal(4, __dbResults);
    this.OWNING_AMT = JdbcWritableBridge.readBigDecimal(5, __dbResults);
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
    JdbcWritableBridge.writeBigDecimal(RCVED_AMT, 3 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(RCVBL_AMT, 4 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(OWNING_AMT, 5 + __off, 2, __dbStmt);
    return 5;
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
        this.RCVED_AMT = null;
    } else {
    this.RCVED_AMT = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.RCVBL_AMT = null;
    } else {
    this.RCVBL_AMT = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.OWNING_AMT = null;
    } else {
    this.OWNING_AMT = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
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
    if (null == this.RCVED_AMT) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.RCVED_AMT, __dataOut);
    }
    if (null == this.RCVBL_AMT) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.RCVBL_AMT, __dataOut);
    }
    if (null == this.OWNING_AMT) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.OWNING_AMT, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(RCVED_AMT==null?"null":RCVED_AMT.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(RCVBL_AMT==null?"null":RCVBL_AMT.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(OWNING_AMT==null?"null":OWNING_AMT.toPlainString(), delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.RCVED_AMT = null; } else {
      this.RCVED_AMT = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.RCVBL_AMT = null; } else {
      this.RCVBL_AMT = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.OWNING_AMT = null; } else {
      this.OWNING_AMT = new java.math.BigDecimal(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_RCVBL_FLOW_PM_S98 o = (BIGDATA_RCVBL_FLOW_PM_S98) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("CONS_NO", this.CONS_NO);
    __sqoop$field_map.put("YM", this.YM);
    __sqoop$field_map.put("RCVED_AMT", this.RCVED_AMT);
    __sqoop$field_map.put("RCVBL_AMT", this.RCVBL_AMT);
    __sqoop$field_map.put("OWNING_AMT", this.OWNING_AMT);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("CONS_NO".equals(__fieldName)) {
      this.CONS_NO = (String) __fieldVal;
    }
    else    if ("YM".equals(__fieldName)) {
      this.YM = (String) __fieldVal;
    }
    else    if ("RCVED_AMT".equals(__fieldName)) {
      this.RCVED_AMT = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("RCVBL_AMT".equals(__fieldName)) {
      this.RCVBL_AMT = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("OWNING_AMT".equals(__fieldName)) {
      this.OWNING_AMT = (java.math.BigDecimal) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
