// ORM class for table 'BIGDATA_USER_INFO_S98'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 09:41:56 CST 2014
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

public class BIGDATA_USER_INFO_S98 extends SqoopRecord  implements DBWritable, Writable {
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
  public BIGDATA_USER_INFO_S98 with_用户ID(java.math.BigDecimal 用户ID) {
    this.用户ID = 用户ID;
    return this;
  }
  private String 客户名称;
  public String get_客户名称() {
    return 客户名称;
  }
  public void set_客户名称(String 客户名称) {
    this.客户名称 = 客户名称;
  }
  public BIGDATA_USER_INFO_S98 with_客户名称(String 客户名称) {
    this.客户名称 = 客户名称;
    return this;
  }
  private String 电价代码;
  public String get_电价代码() {
    return 电价代码;
  }
  public void set_电价代码(String 电价代码) {
    this.电价代码 = 电价代码;
  }
  public BIGDATA_USER_INFO_S98 with_电价代码(String 电价代码) {
    this.电价代码 = 电价代码;
    return this;
  }
  private String 地市局编码;
  public String get_地市局编码() {
    return 地市局编码;
  }
  public void set_地市局编码(String 地市局编码) {
    this.地市局编码 = 地市局编码;
  }
  public BIGDATA_USER_INFO_S98 with_地市局编码(String 地市局编码) {
    this.地市局编码 = 地市局编码;
    return this;
  }
  private String 用电性质;
  public String get_用电性质() {
    return 用电性质;
  }
  public void set_用电性质(String 用电性质) {
    this.用电性质 = 用电性质;
  }
  public BIGDATA_USER_INFO_S98 with_用电性质(String 用电性质) {
    this.用电性质 = 用电性质;
    return this;
  }
  private String 用电类别;
  public String get_用电类别() {
    return 用电类别;
  }
  public void set_用电类别(String 用电类别) {
    this.用电类别 = 用电类别;
  }
  public BIGDATA_USER_INFO_S98 with_用电类别(String 用电类别) {
    this.用电类别 = 用电类别;
    return this;
  }
  private String 供电电压;
  public String get_供电电压() {
    return 供电电压;
  }
  public void set_供电电压(String 供电电压) {
    this.供电电压 = 供电电压;
  }
  public BIGDATA_USER_INFO_S98 with_供电电压(String 供电电压) {
    this.供电电压 = 供电电压;
    return this;
  }
  private java.math.BigDecimal 合同容量;
  public java.math.BigDecimal get_合同容量() {
    return 合同容量;
  }
  public void set_合同容量(java.math.BigDecimal 合同容量) {
    this.合同容量 = 合同容量;
  }
  public BIGDATA_USER_INFO_S98 with_合同容量(java.math.BigDecimal 合同容量) {
    this.合同容量 = 合同容量;
    return this;
  }
  private String 负荷类型;
  public String get_负荷类型() {
    return 负荷类型;
  }
  public void set_负荷类型(String 负荷类型) {
    this.负荷类型 = 负荷类型;
  }
  public BIGDATA_USER_INFO_S98 with_负荷类型(String 负荷类型) {
    this.负荷类型 = 负荷类型;
    return this;
  }
  private String 当前是否销户;
  public String get_当前是否销户() {
    return 当前是否销户;
  }
  public void set_当前是否销户(String 当前是否销户) {
    this.当前是否销户 = 当前是否销户;
  }
  public BIGDATA_USER_INFO_S98 with_当前是否销户(String 当前是否销户) {
    this.当前是否销户 = 当前是否销户;
    return this;
  }
  private String 城镇用户农村用户;
  public String get_城镇用户农村用户() {
    return 城镇用户农村用户;
  }
  public void set_城镇用户农村用户(String 城镇用户农村用户) {
    this.城镇用户农村用户 = 城镇用户农村用户;
  }
  public BIGDATA_USER_INFO_S98 with_城镇用户农村用户(String 城镇用户农村用户) {
    this.城镇用户农村用户 = 城镇用户农村用户;
    return this;
  }
  private String 出账周期;
  public String get_出账周期() {
    return 出账周期;
  }
  public void set_出账周期(String 出账周期) {
    this.出账周期 = 出账周期;
  }
  public BIGDATA_USER_INFO_S98 with_出账周期(String 出账周期) {
    this.出账周期 = 出账周期;
    return this;
  }
  private String 行业;
  public String get_行业() {
    return 行业;
  }
  public void set_行业(String 行业) {
    this.行业 = 行业;
  }
  public BIGDATA_USER_INFO_S98 with_行业(String 行业) {
    this.行业 = 行业;
    return this;
  }
  private String 高压低压;
  public String get_高压低压() {
    return 高压低压;
  }
  public void set_高压低压(String 高压低压) {
    this.高压低压 = 高压低压;
  }
  public BIGDATA_USER_INFO_S98 with_高压低压(String 高压低压) {
    this.高压低压 = 高压低压;
    return this;
  }
  private String 用户号;
  public String get_用户号() {
    return 用户号;
  }
  public void set_用户号(String 用户号) {
    this.用户号 = 用户号;
  }
  public BIGDATA_USER_INFO_S98 with_用户号(String 用户号) {
    this.用户号 = 用户号;
    return this;
  }
  private String 电压;
  public String get_电压() {
    return 电压;
  }
  public void set_电压(String 电压) {
    this.电压 = 电压;
  }
  public BIGDATA_USER_INFO_S98 with_电压(String 电压) {
    this.电压 = 电压;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BIGDATA_USER_INFO_S98)) {
      return false;
    }
    BIGDATA_USER_INFO_S98 that = (BIGDATA_USER_INFO_S98) o;
    boolean equal = true;
    equal = equal && (this.用户ID == null ? that.用户ID == null : this.用户ID.equals(that.用户ID));
    equal = equal && (this.客户名称 == null ? that.客户名称 == null : this.客户名称.equals(that.客户名称));
    equal = equal && (this.电价代码 == null ? that.电价代码 == null : this.电价代码.equals(that.电价代码));
    equal = equal && (this.地市局编码 == null ? that.地市局编码 == null : this.地市局编码.equals(that.地市局编码));
    equal = equal && (this.用电性质 == null ? that.用电性质 == null : this.用电性质.equals(that.用电性质));
    equal = equal && (this.用电类别 == null ? that.用电类别 == null : this.用电类别.equals(that.用电类别));
    equal = equal && (this.供电电压 == null ? that.供电电压 == null : this.供电电压.equals(that.供电电压));
    equal = equal && (this.合同容量 == null ? that.合同容量 == null : this.合同容量.equals(that.合同容量));
    equal = equal && (this.负荷类型 == null ? that.负荷类型 == null : this.负荷类型.equals(that.负荷类型));
    equal = equal && (this.当前是否销户 == null ? that.当前是否销户 == null : this.当前是否销户.equals(that.当前是否销户));
    equal = equal && (this.城镇用户农村用户 == null ? that.城镇用户农村用户 == null : this.城镇用户农村用户.equals(that.城镇用户农村用户));
    equal = equal && (this.出账周期 == null ? that.出账周期 == null : this.出账周期.equals(that.出账周期));
    equal = equal && (this.行业 == null ? that.行业 == null : this.行业.equals(that.行业));
    equal = equal && (this.高压低压 == null ? that.高压低压 == null : this.高压低压.equals(that.高压低压));
    equal = equal && (this.用户号 == null ? that.用户号 == null : this.用户号.equals(that.用户号));
    equal = equal && (this.电压 == null ? that.电压 == null : this.电压.equals(that.电压));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.用户ID = JdbcWritableBridge.readBigDecimal(1, __dbResults);
    this.客户名称 = JdbcWritableBridge.readString(2, __dbResults);
    this.电价代码 = JdbcWritableBridge.readString(3, __dbResults);
    this.地市局编码 = JdbcWritableBridge.readString(4, __dbResults);
    this.用电性质 = JdbcWritableBridge.readString(5, __dbResults);
    this.用电类别 = JdbcWritableBridge.readString(6, __dbResults);
    this.供电电压 = JdbcWritableBridge.readString(7, __dbResults);
    this.合同容量 = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.负荷类型 = JdbcWritableBridge.readString(9, __dbResults);
    this.当前是否销户 = JdbcWritableBridge.readString(10, __dbResults);
    this.城镇用户农村用户 = JdbcWritableBridge.readString(11, __dbResults);
    this.出账周期 = JdbcWritableBridge.readString(12, __dbResults);
    this.行业 = JdbcWritableBridge.readString(13, __dbResults);
    this.高压低压 = JdbcWritableBridge.readString(14, __dbResults);
    this.用户号 = JdbcWritableBridge.readString(15, __dbResults);
    this.电压 = JdbcWritableBridge.readString(16, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeBigDecimal(用户ID, 1 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(客户名称, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(电价代码, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(地市局编码, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(用电性质, 5 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(用电类别, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(供电电压, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(合同容量, 8 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(负荷类型, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(当前是否销户, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(城镇用户农村用户, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(出账周期, 12 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(行业, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(高压低压, 14 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(用户号, 15 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(电压, 16 + __off, 12, __dbStmt);
    return 16;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.用户ID = null;
    } else {
    this.用户ID = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.客户名称 = null;
    } else {
    this.客户名称 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.电价代码 = null;
    } else {
    this.电价代码 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.地市局编码 = null;
    } else {
    this.地市局编码 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.用电性质 = null;
    } else {
    this.用电性质 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.用电类别 = null;
    } else {
    this.用电类别 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.供电电压 = null;
    } else {
    this.供电电压 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.合同容量 = null;
    } else {
    this.合同容量 = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.负荷类型 = null;
    } else {
    this.负荷类型 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.当前是否销户 = null;
    } else {
    this.当前是否销户 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.城镇用户农村用户 = null;
    } else {
    this.城镇用户农村用户 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.出账周期 = null;
    } else {
    this.出账周期 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.行业 = null;
    } else {
    this.行业 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.高压低压 = null;
    } else {
    this.高压低压 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.用户号 = null;
    } else {
    this.用户号 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.电压 = null;
    } else {
    this.电压 = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.用户ID) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.用户ID, __dataOut);
    }
    if (null == this.客户名称) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 客户名称);
    }
    if (null == this.电价代码) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 电价代码);
    }
    if (null == this.地市局编码) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 地市局编码);
    }
    if (null == this.用电性质) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 用电性质);
    }
    if (null == this.用电类别) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 用电类别);
    }
    if (null == this.供电电压) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 供电电压);
    }
    if (null == this.合同容量) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.合同容量, __dataOut);
    }
    if (null == this.负荷类型) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 负荷类型);
    }
    if (null == this.当前是否销户) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 当前是否销户);
    }
    if (null == this.城镇用户农村用户) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 城镇用户农村用户);
    }
    if (null == this.出账周期) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 出账周期);
    }
    if (null == this.行业) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 行业);
    }
    if (null == this.高压低压) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 高压低压);
    }
    if (null == this.用户号) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 用户号);
    }
    if (null == this.电压) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 电压);
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
    __sb.append(FieldFormatter.escapeAndEnclose(客户名称==null?"null":客户名称, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(电价代码==null?"null":电价代码, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(地市局编码==null?"null":地市局编码, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(用电性质==null?"null":用电性质, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(用电类别==null?"null":用电类别, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(供电电压==null?"null":供电电压, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(合同容量==null?"null":合同容量.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(负荷类型==null?"null":负荷类型, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(当前是否销户==null?"null":当前是否销户, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(城镇用户农村用户==null?"null":城镇用户农村用户, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(出账周期==null?"null":出账周期, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(行业==null?"null":行业, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(高压低压==null?"null":高压低压, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(用户号==null?"null":用户号, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(电压==null?"null":电压, delimiters));
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
    if (__cur_str.equals("null")) { this.客户名称 = null; } else {
      this.客户名称 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.电价代码 = null; } else {
      this.电价代码 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.地市局编码 = null; } else {
      this.地市局编码 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.用电性质 = null; } else {
      this.用电性质 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.用电类别 = null; } else {
      this.用电类别 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.供电电压 = null; } else {
      this.供电电压 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.合同容量 = null; } else {
      this.合同容量 = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.负荷类型 = null; } else {
      this.负荷类型 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.当前是否销户 = null; } else {
      this.当前是否销户 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.城镇用户农村用户 = null; } else {
      this.城镇用户农村用户 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.出账周期 = null; } else {
      this.出账周期 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.行业 = null; } else {
      this.行业 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.高压低压 = null; } else {
      this.高压低压 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.用户号 = null; } else {
      this.用户号 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.电压 = null; } else {
      this.电压 = __cur_str;
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_USER_INFO_S98 o = (BIGDATA_USER_INFO_S98) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("用户ID", this.用户ID);
    __sqoop$field_map.put("客户名称", this.客户名称);
    __sqoop$field_map.put("电价代码", this.电价代码);
    __sqoop$field_map.put("地市局编码", this.地市局编码);
    __sqoop$field_map.put("用电性质", this.用电性质);
    __sqoop$field_map.put("用电类别", this.用电类别);
    __sqoop$field_map.put("供电电压", this.供电电压);
    __sqoop$field_map.put("合同容量", this.合同容量);
    __sqoop$field_map.put("负荷类型", this.负荷类型);
    __sqoop$field_map.put("当前是否销户", this.当前是否销户);
    __sqoop$field_map.put("城镇用户农村用户", this.城镇用户农村用户);
    __sqoop$field_map.put("出账周期", this.出账周期);
    __sqoop$field_map.put("行业", this.行业);
    __sqoop$field_map.put("高压低压", this.高压低压);
    __sqoop$field_map.put("用户号", this.用户号);
    __sqoop$field_map.put("电压", this.电压);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("用户ID".equals(__fieldName)) {
      this.用户ID = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("客户名称".equals(__fieldName)) {
      this.客户名称 = (String) __fieldVal;
    }
    else    if ("电价代码".equals(__fieldName)) {
      this.电价代码 = (String) __fieldVal;
    }
    else    if ("地市局编码".equals(__fieldName)) {
      this.地市局编码 = (String) __fieldVal;
    }
    else    if ("用电性质".equals(__fieldName)) {
      this.用电性质 = (String) __fieldVal;
    }
    else    if ("用电类别".equals(__fieldName)) {
      this.用电类别 = (String) __fieldVal;
    }
    else    if ("供电电压".equals(__fieldName)) {
      this.供电电压 = (String) __fieldVal;
    }
    else    if ("合同容量".equals(__fieldName)) {
      this.合同容量 = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("负荷类型".equals(__fieldName)) {
      this.负荷类型 = (String) __fieldVal;
    }
    else    if ("当前是否销户".equals(__fieldName)) {
      this.当前是否销户 = (String) __fieldVal;
    }
    else    if ("城镇用户农村用户".equals(__fieldName)) {
      this.城镇用户农村用户 = (String) __fieldVal;
    }
    else    if ("出账周期".equals(__fieldName)) {
      this.出账周期 = (String) __fieldVal;
    }
    else    if ("行业".equals(__fieldName)) {
      this.行业 = (String) __fieldVal;
    }
    else    if ("高压低压".equals(__fieldName)) {
      this.高压低压 = (String) __fieldVal;
    }
    else    if ("用户号".equals(__fieldName)) {
      this.用户号 = (String) __fieldVal;
    }
    else    if ("电压".equals(__fieldName)) {
      this.电压 = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
