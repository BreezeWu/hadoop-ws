// ORM class for table 'BIGDATA_VOLUME_OF_TS_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 16:17:52 CST 2014
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
  private java.math.BigDecimal KWH_VOLUME_TOP;
  public java.math.BigDecimal get_KWH_VOLUME_TOP() {
    return KWH_VOLUME_TOP;
  }
  public void set_KWH_VOLUME_TOP(java.math.BigDecimal KWH_VOLUME_TOP) {
    this.KWH_VOLUME_TOP = KWH_VOLUME_TOP;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_VOLUME_TOP(java.math.BigDecimal KWH_VOLUME_TOP) {
    this.KWH_VOLUME_TOP = KWH_VOLUME_TOP;
    return this;
  }
  private java.math.BigDecimal KWH_PRC_TOP;
  public java.math.BigDecimal get_KWH_PRC_TOP() {
    return KWH_PRC_TOP;
  }
  public void set_KWH_PRC_TOP(java.math.BigDecimal KWH_PRC_TOP) {
    this.KWH_PRC_TOP = KWH_PRC_TOP;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_PRC_TOP(java.math.BigDecimal KWH_PRC_TOP) {
    this.KWH_PRC_TOP = KWH_PRC_TOP;
    return this;
  }
  private java.math.BigDecimal KWH_VOLUME_HIGH;
  public java.math.BigDecimal get_KWH_VOLUME_HIGH() {
    return KWH_VOLUME_HIGH;
  }
  public void set_KWH_VOLUME_HIGH(java.math.BigDecimal KWH_VOLUME_HIGH) {
    this.KWH_VOLUME_HIGH = KWH_VOLUME_HIGH;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_VOLUME_HIGH(java.math.BigDecimal KWH_VOLUME_HIGH) {
    this.KWH_VOLUME_HIGH = KWH_VOLUME_HIGH;
    return this;
  }
  private java.math.BigDecimal KWH_PRC_HIGH;
  public java.math.BigDecimal get_KWH_PRC_HIGH() {
    return KWH_PRC_HIGH;
  }
  public void set_KWH_PRC_HIGH(java.math.BigDecimal KWH_PRC_HIGH) {
    this.KWH_PRC_HIGH = KWH_PRC_HIGH;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_PRC_HIGH(java.math.BigDecimal KWH_PRC_HIGH) {
    this.KWH_PRC_HIGH = KWH_PRC_HIGH;
    return this;
  }
  private java.math.BigDecimal KWH_VOLUME_MEAN;
  public java.math.BigDecimal get_KWH_VOLUME_MEAN() {
    return KWH_VOLUME_MEAN;
  }
  public void set_KWH_VOLUME_MEAN(java.math.BigDecimal KWH_VOLUME_MEAN) {
    this.KWH_VOLUME_MEAN = KWH_VOLUME_MEAN;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_VOLUME_MEAN(java.math.BigDecimal KWH_VOLUME_MEAN) {
    this.KWH_VOLUME_MEAN = KWH_VOLUME_MEAN;
    return this;
  }
  private java.math.BigDecimal KWH_PRC_MEAN;
  public java.math.BigDecimal get_KWH_PRC_MEAN() {
    return KWH_PRC_MEAN;
  }
  public void set_KWH_PRC_MEAN(java.math.BigDecimal KWH_PRC_MEAN) {
    this.KWH_PRC_MEAN = KWH_PRC_MEAN;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_PRC_MEAN(java.math.BigDecimal KWH_PRC_MEAN) {
    this.KWH_PRC_MEAN = KWH_PRC_MEAN;
    return this;
  }
  private java.math.BigDecimal KWH_VOLUME_LOW;
  public java.math.BigDecimal get_KWH_VOLUME_LOW() {
    return KWH_VOLUME_LOW;
  }
  public void set_KWH_VOLUME_LOW(java.math.BigDecimal KWH_VOLUME_LOW) {
    this.KWH_VOLUME_LOW = KWH_VOLUME_LOW;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_VOLUME_LOW(java.math.BigDecimal KWH_VOLUME_LOW) {
    this.KWH_VOLUME_LOW = KWH_VOLUME_LOW;
    return this;
  }
  private java.math.BigDecimal KWH_PRC_LOW;
  public java.math.BigDecimal get_KWH_PRC_LOW() {
    return KWH_PRC_LOW;
  }
  public void set_KWH_PRC_LOW(java.math.BigDecimal KWH_PRC_LOW) {
    this.KWH_PRC_LOW = KWH_PRC_LOW;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_PRC_LOW(java.math.BigDecimal KWH_PRC_LOW) {
    this.KWH_PRC_LOW = KWH_PRC_LOW;
    return this;
  }
  private java.math.BigDecimal KWH_VOLUME_BOTTOM;
  public java.math.BigDecimal get_KWH_VOLUME_BOTTOM() {
    return KWH_VOLUME_BOTTOM;
  }
  public void set_KWH_VOLUME_BOTTOM(java.math.BigDecimal KWH_VOLUME_BOTTOM) {
    this.KWH_VOLUME_BOTTOM = KWH_VOLUME_BOTTOM;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_VOLUME_BOTTOM(java.math.BigDecimal KWH_VOLUME_BOTTOM) {
    this.KWH_VOLUME_BOTTOM = KWH_VOLUME_BOTTOM;
    return this;
  }
  private java.math.BigDecimal KWH_PRC_BOTTOM;
  public java.math.BigDecimal get_KWH_PRC_BOTTOM() {
    return KWH_PRC_BOTTOM;
  }
  public void set_KWH_PRC_BOTTOM(java.math.BigDecimal KWH_PRC_BOTTOM) {
    this.KWH_PRC_BOTTOM = KWH_PRC_BOTTOM;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_KWH_PRC_BOTTOM(java.math.BigDecimal KWH_PRC_BOTTOM) {
    this.KWH_PRC_BOTTOM = KWH_PRC_BOTTOM;
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
    equal = equal && (this.KWH_VOLUME_TOP == null ? that.KWH_VOLUME_TOP == null : this.KWH_VOLUME_TOP.equals(that.KWH_VOLUME_TOP));
    equal = equal && (this.KWH_PRC_TOP == null ? that.KWH_PRC_TOP == null : this.KWH_PRC_TOP.equals(that.KWH_PRC_TOP));
    equal = equal && (this.KWH_VOLUME_HIGH == null ? that.KWH_VOLUME_HIGH == null : this.KWH_VOLUME_HIGH.equals(that.KWH_VOLUME_HIGH));
    equal = equal && (this.KWH_PRC_HIGH == null ? that.KWH_PRC_HIGH == null : this.KWH_PRC_HIGH.equals(that.KWH_PRC_HIGH));
    equal = equal && (this.KWH_VOLUME_MEAN == null ? that.KWH_VOLUME_MEAN == null : this.KWH_VOLUME_MEAN.equals(that.KWH_VOLUME_MEAN));
    equal = equal && (this.KWH_PRC_MEAN == null ? that.KWH_PRC_MEAN == null : this.KWH_PRC_MEAN.equals(that.KWH_PRC_MEAN));
    equal = equal && (this.KWH_VOLUME_LOW == null ? that.KWH_VOLUME_LOW == null : this.KWH_VOLUME_LOW.equals(that.KWH_VOLUME_LOW));
    equal = equal && (this.KWH_PRC_LOW == null ? that.KWH_PRC_LOW == null : this.KWH_PRC_LOW.equals(that.KWH_PRC_LOW));
    equal = equal && (this.KWH_VOLUME_BOTTOM == null ? that.KWH_VOLUME_BOTTOM == null : this.KWH_VOLUME_BOTTOM.equals(that.KWH_VOLUME_BOTTOM));
    equal = equal && (this.KWH_PRC_BOTTOM == null ? that.KWH_PRC_BOTTOM == null : this.KWH_PRC_BOTTOM.equals(that.KWH_PRC_BOTTOM));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.CONS_NO = JdbcWritableBridge.readString(1, __dbResults);
    this.YM = JdbcWritableBridge.readString(2, __dbResults);
    this.KWH_VOLUME_TOP = JdbcWritableBridge.readBigDecimal(3, __dbResults);
    this.KWH_PRC_TOP = JdbcWritableBridge.readBigDecimal(4, __dbResults);
    this.KWH_VOLUME_HIGH = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.KWH_PRC_HIGH = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.KWH_VOLUME_MEAN = JdbcWritableBridge.readBigDecimal(7, __dbResults);
    this.KWH_PRC_MEAN = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.KWH_VOLUME_LOW = JdbcWritableBridge.readBigDecimal(9, __dbResults);
    this.KWH_PRC_LOW = JdbcWritableBridge.readBigDecimal(10, __dbResults);
    this.KWH_VOLUME_BOTTOM = JdbcWritableBridge.readBigDecimal(11, __dbResults);
    this.KWH_PRC_BOTTOM = JdbcWritableBridge.readBigDecimal(12, __dbResults);
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
    JdbcWritableBridge.writeBigDecimal(KWH_VOLUME_TOP, 3 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(KWH_PRC_TOP, 4 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(KWH_VOLUME_HIGH, 5 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(KWH_PRC_HIGH, 6 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(KWH_VOLUME_MEAN, 7 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(KWH_PRC_MEAN, 8 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(KWH_VOLUME_LOW, 9 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(KWH_PRC_LOW, 10 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(KWH_VOLUME_BOTTOM, 11 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(KWH_PRC_BOTTOM, 12 + __off, 2, __dbStmt);
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
        this.KWH_VOLUME_TOP = null;
    } else {
    this.KWH_VOLUME_TOP = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.KWH_PRC_TOP = null;
    } else {
    this.KWH_PRC_TOP = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.KWH_VOLUME_HIGH = null;
    } else {
    this.KWH_VOLUME_HIGH = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.KWH_PRC_HIGH = null;
    } else {
    this.KWH_PRC_HIGH = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.KWH_VOLUME_MEAN = null;
    } else {
    this.KWH_VOLUME_MEAN = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.KWH_PRC_MEAN = null;
    } else {
    this.KWH_PRC_MEAN = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.KWH_VOLUME_LOW = null;
    } else {
    this.KWH_VOLUME_LOW = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.KWH_PRC_LOW = null;
    } else {
    this.KWH_PRC_LOW = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.KWH_VOLUME_BOTTOM = null;
    } else {
    this.KWH_VOLUME_BOTTOM = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.KWH_PRC_BOTTOM = null;
    } else {
    this.KWH_PRC_BOTTOM = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
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
    if (null == this.KWH_VOLUME_TOP) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_VOLUME_TOP, __dataOut);
    }
    if (null == this.KWH_PRC_TOP) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_PRC_TOP, __dataOut);
    }
    if (null == this.KWH_VOLUME_HIGH) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_VOLUME_HIGH, __dataOut);
    }
    if (null == this.KWH_PRC_HIGH) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_PRC_HIGH, __dataOut);
    }
    if (null == this.KWH_VOLUME_MEAN) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_VOLUME_MEAN, __dataOut);
    }
    if (null == this.KWH_PRC_MEAN) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_PRC_MEAN, __dataOut);
    }
    if (null == this.KWH_VOLUME_LOW) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_VOLUME_LOW, __dataOut);
    }
    if (null == this.KWH_PRC_LOW) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_PRC_LOW, __dataOut);
    }
    if (null == this.KWH_VOLUME_BOTTOM) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_VOLUME_BOTTOM, __dataOut);
    }
    if (null == this.KWH_PRC_BOTTOM) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.KWH_PRC_BOTTOM, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_VOLUME_TOP==null?"null":KWH_VOLUME_TOP.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_PRC_TOP==null?"null":KWH_PRC_TOP.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_VOLUME_HIGH==null?"null":KWH_VOLUME_HIGH.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_PRC_HIGH==null?"null":KWH_PRC_HIGH.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_VOLUME_MEAN==null?"null":KWH_VOLUME_MEAN.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_PRC_MEAN==null?"null":KWH_PRC_MEAN.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_VOLUME_LOW==null?"null":KWH_VOLUME_LOW.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_PRC_LOW==null?"null":KWH_PRC_LOW.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_VOLUME_BOTTOM==null?"null":KWH_VOLUME_BOTTOM.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(KWH_PRC_BOTTOM==null?"null":KWH_PRC_BOTTOM.toPlainString(), delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_VOLUME_TOP = null; } else {
      this.KWH_VOLUME_TOP = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_PRC_TOP = null; } else {
      this.KWH_PRC_TOP = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_VOLUME_HIGH = null; } else {
      this.KWH_VOLUME_HIGH = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_PRC_HIGH = null; } else {
      this.KWH_PRC_HIGH = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_VOLUME_MEAN = null; } else {
      this.KWH_VOLUME_MEAN = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_PRC_MEAN = null; } else {
      this.KWH_PRC_MEAN = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_VOLUME_LOW = null; } else {
      this.KWH_VOLUME_LOW = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_PRC_LOW = null; } else {
      this.KWH_PRC_LOW = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_VOLUME_BOTTOM = null; } else {
      this.KWH_VOLUME_BOTTOM = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.KWH_PRC_BOTTOM = null; } else {
      this.KWH_PRC_BOTTOM = new java.math.BigDecimal(__cur_str);
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
    __sqoop$field_map.put("KWH_VOLUME_TOP", this.KWH_VOLUME_TOP);
    __sqoop$field_map.put("KWH_PRC_TOP", this.KWH_PRC_TOP);
    __sqoop$field_map.put("KWH_VOLUME_HIGH", this.KWH_VOLUME_HIGH);
    __sqoop$field_map.put("KWH_PRC_HIGH", this.KWH_PRC_HIGH);
    __sqoop$field_map.put("KWH_VOLUME_MEAN", this.KWH_VOLUME_MEAN);
    __sqoop$field_map.put("KWH_PRC_MEAN", this.KWH_PRC_MEAN);
    __sqoop$field_map.put("KWH_VOLUME_LOW", this.KWH_VOLUME_LOW);
    __sqoop$field_map.put("KWH_PRC_LOW", this.KWH_PRC_LOW);
    __sqoop$field_map.put("KWH_VOLUME_BOTTOM", this.KWH_VOLUME_BOTTOM);
    __sqoop$field_map.put("KWH_PRC_BOTTOM", this.KWH_PRC_BOTTOM);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("CONS_NO".equals(__fieldName)) {
      this.CONS_NO = (String) __fieldVal;
    }
    else    if ("YM".equals(__fieldName)) {
      this.YM = (String) __fieldVal;
    }
    else    if ("KWH_VOLUME_TOP".equals(__fieldName)) {
      this.KWH_VOLUME_TOP = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("KWH_PRC_TOP".equals(__fieldName)) {
      this.KWH_PRC_TOP = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("KWH_VOLUME_HIGH".equals(__fieldName)) {
      this.KWH_VOLUME_HIGH = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("KWH_PRC_HIGH".equals(__fieldName)) {
      this.KWH_PRC_HIGH = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("KWH_VOLUME_MEAN".equals(__fieldName)) {
      this.KWH_VOLUME_MEAN = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("KWH_PRC_MEAN".equals(__fieldName)) {
      this.KWH_PRC_MEAN = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("KWH_VOLUME_LOW".equals(__fieldName)) {
      this.KWH_VOLUME_LOW = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("KWH_PRC_LOW".equals(__fieldName)) {
      this.KWH_PRC_LOW = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("KWH_VOLUME_BOTTOM".equals(__fieldName)) {
      this.KWH_VOLUME_BOTTOM = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("KWH_PRC_BOTTOM".equals(__fieldName)) {
      this.KWH_PRC_BOTTOM = (java.math.BigDecimal) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
