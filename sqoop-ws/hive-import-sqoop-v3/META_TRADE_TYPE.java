// ORM class for table 'META_TRADE_TYPE'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 09:39:33 CST 2014
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

public class META_TRADE_TYPE extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private String TRADE_CODE;
  public String get_TRADE_CODE() {
    return TRADE_CODE;
  }
  public void set_TRADE_CODE(String TRADE_CODE) {
    this.TRADE_CODE = TRADE_CODE;
  }
  public META_TRADE_TYPE with_TRADE_CODE(String TRADE_CODE) {
    this.TRADE_CODE = TRADE_CODE;
    return this;
  }
  private java.math.BigDecimal SERIAL;
  public java.math.BigDecimal get_SERIAL() {
    return SERIAL;
  }
  public void set_SERIAL(java.math.BigDecimal SERIAL) {
    this.SERIAL = SERIAL;
  }
  public META_TRADE_TYPE with_SERIAL(java.math.BigDecimal SERIAL) {
    this.SERIAL = SERIAL;
    return this;
  }
  private String TRADE;
  public String get_TRADE() {
    return TRADE;
  }
  public void set_TRADE(String TRADE) {
    this.TRADE = TRADE;
  }
  public META_TRADE_TYPE with_TRADE(String TRADE) {
    this.TRADE = TRADE;
    return this;
  }
  private String RPT_TAG;
  public String get_RPT_TAG() {
    return RPT_TAG;
  }
  public void set_RPT_TAG(String RPT_TAG) {
    this.RPT_TAG = RPT_TAG;
  }
  public META_TRADE_TYPE with_RPT_TAG(String RPT_TAG) {
    this.RPT_TAG = RPT_TAG;
    return this;
  }
  private String ARREAR_TRADE;
  public String get_ARREAR_TRADE() {
    return ARREAR_TRADE;
  }
  public void set_ARREAR_TRADE(String ARREAR_TRADE) {
    this.ARREAR_TRADE = ARREAR_TRADE;
  }
  public META_TRADE_TYPE with_ARREAR_TRADE(String ARREAR_TRADE) {
    this.ARREAR_TRADE = ARREAR_TRADE;
    return this;
  }
  private String VISIBLE_TAG;
  public String get_VISIBLE_TAG() {
    return VISIBLE_TAG;
  }
  public void set_VISIBLE_TAG(String VISIBLE_TAG) {
    this.VISIBLE_TAG = VISIBLE_TAG;
  }
  public META_TRADE_TYPE with_VISIBLE_TAG(String VISIBLE_TAG) {
    this.VISIBLE_TAG = VISIBLE_TAG;
    return this;
  }
  private String SUPERIOR_TRADE;
  public String get_SUPERIOR_TRADE() {
    return SUPERIOR_TRADE;
  }
  public void set_SUPERIOR_TRADE(String SUPERIOR_TRADE) {
    this.SUPERIOR_TRADE = SUPERIOR_TRADE;
  }
  public META_TRADE_TYPE with_SUPERIOR_TRADE(String SUPERIOR_TRADE) {
    this.SUPERIOR_TRADE = SUPERIOR_TRADE;
    return this;
  }
  private String DEGREE_TRADE;
  public String get_DEGREE_TRADE() {
    return DEGREE_TRADE;
  }
  public void set_DEGREE_TRADE(String DEGREE_TRADE) {
    this.DEGREE_TRADE = DEGREE_TRADE;
  }
  public META_TRADE_TYPE with_DEGREE_TRADE(String DEGREE_TRADE) {
    this.DEGREE_TRADE = DEGREE_TRADE;
    return this;
  }
  private java.math.BigDecimal TRADE_LEVEL;
  public java.math.BigDecimal get_TRADE_LEVEL() {
    return TRADE_LEVEL;
  }
  public void set_TRADE_LEVEL(java.math.BigDecimal TRADE_LEVEL) {
    this.TRADE_LEVEL = TRADE_LEVEL;
  }
  public META_TRADE_TYPE with_TRADE_LEVEL(java.math.BigDecimal TRADE_LEVEL) {
    this.TRADE_LEVEL = TRADE_LEVEL;
    return this;
  }
  private String SUPER0;
  public String get_SUPER0() {
    return SUPER0;
  }
  public void set_SUPER0(String SUPER0) {
    this.SUPER0 = SUPER0;
  }
  public META_TRADE_TYPE with_SUPER0(String SUPER0) {
    this.SUPER0 = SUPER0;
    return this;
  }
  private String SUPER1;
  public String get_SUPER1() {
    return SUPER1;
  }
  public void set_SUPER1(String SUPER1) {
    this.SUPER1 = SUPER1;
  }
  public META_TRADE_TYPE with_SUPER1(String SUPER1) {
    this.SUPER1 = SUPER1;
    return this;
  }
  private String SUPER2;
  public String get_SUPER2() {
    return SUPER2;
  }
  public void set_SUPER2(String SUPER2) {
    this.SUPER2 = SUPER2;
  }
  public META_TRADE_TYPE with_SUPER2(String SUPER2) {
    this.SUPER2 = SUPER2;
    return this;
  }
  private String SUPER3;
  public String get_SUPER3() {
    return SUPER3;
  }
  public void set_SUPER3(String SUPER3) {
    this.SUPER3 = SUPER3;
  }
  public META_TRADE_TYPE with_SUPER3(String SUPER3) {
    this.SUPER3 = SUPER3;
    return this;
  }
  private String SUPER4;
  public String get_SUPER4() {
    return SUPER4;
  }
  public void set_SUPER4(String SUPER4) {
    this.SUPER4 = SUPER4;
  }
  public META_TRADE_TYPE with_SUPER4(String SUPER4) {
    this.SUPER4 = SUPER4;
    return this;
  }
  private String SUPER5;
  public String get_SUPER5() {
    return SUPER5;
  }
  public void set_SUPER5(String SUPER5) {
    this.SUPER5 = SUPER5;
  }
  public META_TRADE_TYPE with_SUPER5(String SUPER5) {
    this.SUPER5 = SUPER5;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof META_TRADE_TYPE)) {
      return false;
    }
    META_TRADE_TYPE that = (META_TRADE_TYPE) o;
    boolean equal = true;
    equal = equal && (this.TRADE_CODE == null ? that.TRADE_CODE == null : this.TRADE_CODE.equals(that.TRADE_CODE));
    equal = equal && (this.SERIAL == null ? that.SERIAL == null : this.SERIAL.equals(that.SERIAL));
    equal = equal && (this.TRADE == null ? that.TRADE == null : this.TRADE.equals(that.TRADE));
    equal = equal && (this.RPT_TAG == null ? that.RPT_TAG == null : this.RPT_TAG.equals(that.RPT_TAG));
    equal = equal && (this.ARREAR_TRADE == null ? that.ARREAR_TRADE == null : this.ARREAR_TRADE.equals(that.ARREAR_TRADE));
    equal = equal && (this.VISIBLE_TAG == null ? that.VISIBLE_TAG == null : this.VISIBLE_TAG.equals(that.VISIBLE_TAG));
    equal = equal && (this.SUPERIOR_TRADE == null ? that.SUPERIOR_TRADE == null : this.SUPERIOR_TRADE.equals(that.SUPERIOR_TRADE));
    equal = equal && (this.DEGREE_TRADE == null ? that.DEGREE_TRADE == null : this.DEGREE_TRADE.equals(that.DEGREE_TRADE));
    equal = equal && (this.TRADE_LEVEL == null ? that.TRADE_LEVEL == null : this.TRADE_LEVEL.equals(that.TRADE_LEVEL));
    equal = equal && (this.SUPER0 == null ? that.SUPER0 == null : this.SUPER0.equals(that.SUPER0));
    equal = equal && (this.SUPER1 == null ? that.SUPER1 == null : this.SUPER1.equals(that.SUPER1));
    equal = equal && (this.SUPER2 == null ? that.SUPER2 == null : this.SUPER2.equals(that.SUPER2));
    equal = equal && (this.SUPER3 == null ? that.SUPER3 == null : this.SUPER3.equals(that.SUPER3));
    equal = equal && (this.SUPER4 == null ? that.SUPER4 == null : this.SUPER4.equals(that.SUPER4));
    equal = equal && (this.SUPER5 == null ? that.SUPER5 == null : this.SUPER5.equals(that.SUPER5));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.TRADE_CODE = JdbcWritableBridge.readString(1, __dbResults);
    this.SERIAL = JdbcWritableBridge.readBigDecimal(2, __dbResults);
    this.TRADE = JdbcWritableBridge.readString(3, __dbResults);
    this.RPT_TAG = JdbcWritableBridge.readString(4, __dbResults);
    this.ARREAR_TRADE = JdbcWritableBridge.readString(5, __dbResults);
    this.VISIBLE_TAG = JdbcWritableBridge.readString(6, __dbResults);
    this.SUPERIOR_TRADE = JdbcWritableBridge.readString(7, __dbResults);
    this.DEGREE_TRADE = JdbcWritableBridge.readString(8, __dbResults);
    this.TRADE_LEVEL = JdbcWritableBridge.readBigDecimal(9, __dbResults);
    this.SUPER0 = JdbcWritableBridge.readString(10, __dbResults);
    this.SUPER1 = JdbcWritableBridge.readString(11, __dbResults);
    this.SUPER2 = JdbcWritableBridge.readString(12, __dbResults);
    this.SUPER3 = JdbcWritableBridge.readString(13, __dbResults);
    this.SUPER4 = JdbcWritableBridge.readString(14, __dbResults);
    this.SUPER5 = JdbcWritableBridge.readString(15, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(TRADE_CODE, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(SERIAL, 2 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(TRADE, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(RPT_TAG, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ARREAR_TRADE, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(VISIBLE_TAG, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(SUPERIOR_TRADE, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(DEGREE_TRADE, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(TRADE_LEVEL, 9 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(SUPER0, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(SUPER1, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(SUPER2, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(SUPER3, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(SUPER4, 14 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(SUPER5, 15 + __off, 12, __dbStmt);
    return 15;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.TRADE_CODE = null;
    } else {
    this.TRADE_CODE = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SERIAL = null;
    } else {
    this.SERIAL = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.TRADE = null;
    } else {
    this.TRADE = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.RPT_TAG = null;
    } else {
    this.RPT_TAG = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ARREAR_TRADE = null;
    } else {
    this.ARREAR_TRADE = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.VISIBLE_TAG = null;
    } else {
    this.VISIBLE_TAG = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SUPERIOR_TRADE = null;
    } else {
    this.SUPERIOR_TRADE = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.DEGREE_TRADE = null;
    } else {
    this.DEGREE_TRADE = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.TRADE_LEVEL = null;
    } else {
    this.TRADE_LEVEL = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SUPER0 = null;
    } else {
    this.SUPER0 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SUPER1 = null;
    } else {
    this.SUPER1 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SUPER2 = null;
    } else {
    this.SUPER2 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SUPER3 = null;
    } else {
    this.SUPER3 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SUPER4 = null;
    } else {
    this.SUPER4 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SUPER5 = null;
    } else {
    this.SUPER5 = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.TRADE_CODE) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, TRADE_CODE);
    }
    if (null == this.SERIAL) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.SERIAL, __dataOut);
    }
    if (null == this.TRADE) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, TRADE);
    }
    if (null == this.RPT_TAG) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, RPT_TAG);
    }
    if (null == this.ARREAR_TRADE) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ARREAR_TRADE);
    }
    if (null == this.VISIBLE_TAG) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, VISIBLE_TAG);
    }
    if (null == this.SUPERIOR_TRADE) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, SUPERIOR_TRADE);
    }
    if (null == this.DEGREE_TRADE) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, DEGREE_TRADE);
    }
    if (null == this.TRADE_LEVEL) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.TRADE_LEVEL, __dataOut);
    }
    if (null == this.SUPER0) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, SUPER0);
    }
    if (null == this.SUPER1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, SUPER1);
    }
    if (null == this.SUPER2) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, SUPER2);
    }
    if (null == this.SUPER3) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, SUPER3);
    }
    if (null == this.SUPER4) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, SUPER4);
    }
    if (null == this.SUPER5) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, SUPER5);
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
    __sb.append(FieldFormatter.escapeAndEnclose(TRADE_CODE==null?"null":TRADE_CODE, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SERIAL==null?"null":SERIAL.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(TRADE==null?"null":TRADE, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(RPT_TAG==null?"null":RPT_TAG, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ARREAR_TRADE==null?"null":ARREAR_TRADE, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(VISIBLE_TAG==null?"null":VISIBLE_TAG, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SUPERIOR_TRADE==null?"null":SUPERIOR_TRADE, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(DEGREE_TRADE==null?"null":DEGREE_TRADE, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(TRADE_LEVEL==null?"null":TRADE_LEVEL.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SUPER0==null?"null":SUPER0, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SUPER1==null?"null":SUPER1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SUPER2==null?"null":SUPER2, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SUPER3==null?"null":SUPER3, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SUPER4==null?"null":SUPER4, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SUPER5==null?"null":SUPER5, delimiters));
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
    if (__cur_str.equals("null")) { this.TRADE_CODE = null; } else {
      this.TRADE_CODE = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SERIAL = null; } else {
      this.SERIAL = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.TRADE = null; } else {
      this.TRADE = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.RPT_TAG = null; } else {
      this.RPT_TAG = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ARREAR_TRADE = null; } else {
      this.ARREAR_TRADE = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.VISIBLE_TAG = null; } else {
      this.VISIBLE_TAG = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.SUPERIOR_TRADE = null; } else {
      this.SUPERIOR_TRADE = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.DEGREE_TRADE = null; } else {
      this.DEGREE_TRADE = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.TRADE_LEVEL = null; } else {
      this.TRADE_LEVEL = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.SUPER0 = null; } else {
      this.SUPER0 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.SUPER1 = null; } else {
      this.SUPER1 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.SUPER2 = null; } else {
      this.SUPER2 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.SUPER3 = null; } else {
      this.SUPER3 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.SUPER4 = null; } else {
      this.SUPER4 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.SUPER5 = null; } else {
      this.SUPER5 = __cur_str;
    }

  }

  public Object clone() throws CloneNotSupportedException {
    META_TRADE_TYPE o = (META_TRADE_TYPE) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("TRADE_CODE", this.TRADE_CODE);
    __sqoop$field_map.put("SERIAL", this.SERIAL);
    __sqoop$field_map.put("TRADE", this.TRADE);
    __sqoop$field_map.put("RPT_TAG", this.RPT_TAG);
    __sqoop$field_map.put("ARREAR_TRADE", this.ARREAR_TRADE);
    __sqoop$field_map.put("VISIBLE_TAG", this.VISIBLE_TAG);
    __sqoop$field_map.put("SUPERIOR_TRADE", this.SUPERIOR_TRADE);
    __sqoop$field_map.put("DEGREE_TRADE", this.DEGREE_TRADE);
    __sqoop$field_map.put("TRADE_LEVEL", this.TRADE_LEVEL);
    __sqoop$field_map.put("SUPER0", this.SUPER0);
    __sqoop$field_map.put("SUPER1", this.SUPER1);
    __sqoop$field_map.put("SUPER2", this.SUPER2);
    __sqoop$field_map.put("SUPER3", this.SUPER3);
    __sqoop$field_map.put("SUPER4", this.SUPER4);
    __sqoop$field_map.put("SUPER5", this.SUPER5);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("TRADE_CODE".equals(__fieldName)) {
      this.TRADE_CODE = (String) __fieldVal;
    }
    else    if ("SERIAL".equals(__fieldName)) {
      this.SERIAL = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("TRADE".equals(__fieldName)) {
      this.TRADE = (String) __fieldVal;
    }
    else    if ("RPT_TAG".equals(__fieldName)) {
      this.RPT_TAG = (String) __fieldVal;
    }
    else    if ("ARREAR_TRADE".equals(__fieldName)) {
      this.ARREAR_TRADE = (String) __fieldVal;
    }
    else    if ("VISIBLE_TAG".equals(__fieldName)) {
      this.VISIBLE_TAG = (String) __fieldVal;
    }
    else    if ("SUPERIOR_TRADE".equals(__fieldName)) {
      this.SUPERIOR_TRADE = (String) __fieldVal;
    }
    else    if ("DEGREE_TRADE".equals(__fieldName)) {
      this.DEGREE_TRADE = (String) __fieldVal;
    }
    else    if ("TRADE_LEVEL".equals(__fieldName)) {
      this.TRADE_LEVEL = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("SUPER0".equals(__fieldName)) {
      this.SUPER0 = (String) __fieldVal;
    }
    else    if ("SUPER1".equals(__fieldName)) {
      this.SUPER1 = (String) __fieldVal;
    }
    else    if ("SUPER2".equals(__fieldName)) {
      this.SUPER2 = (String) __fieldVal;
    }
    else    if ("SUPER3".equals(__fieldName)) {
      this.SUPER3 = (String) __fieldVal;
    }
    else    if ("SUPER4".equals(__fieldName)) {
      this.SUPER4 = (String) __fieldVal;
    }
    else    if ("SUPER5".equals(__fieldName)) {
      this.SUPER5 = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
