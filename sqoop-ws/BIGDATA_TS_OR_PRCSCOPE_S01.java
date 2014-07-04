// ORM class for table 'BIGDATA_TS_OR_PRCSCOPE_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Fri Jul 04 16:50:03 CST 2014
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

public class BIGDATA_TS_OR_PRCSCOPE_S01 extends SqoopRecord  implements DBWritable, Writable {
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
  public BIGDATA_TS_OR_PRCSCOPE_S01 with_用户号(String 用户号) {
    this.用户号 = 用户号;
    return this;
  }
  private String 电价名称;
  public String get_电价名称() {
    return 电价名称;
  }
  public void set_电价名称(String 电价名称) {
    this.电价名称 = 电价名称;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S01 with_电价名称(String 电价名称) {
    this.电价名称 = 电价名称;
    return this;
  }
  private String 是否分时;
  public String get_是否分时() {
    return 是否分时;
  }
  public void set_是否分时(String 是否分时) {
    this.是否分时 = 是否分时;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S01 with_是否分时(String 是否分时) {
    this.是否分时 = 是否分时;
    return this;
  }
  private String 是否阶梯;
  public String get_是否阶梯() {
    return 是否阶梯;
  }
  public void set_是否阶梯(String 是否阶梯) {
    this.是否阶梯 = 是否阶梯;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S01 with_是否阶梯(String 是否阶梯) {
    this.是否阶梯 = 是否阶梯;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BIGDATA_TS_OR_PRCSCOPE_S01)) {
      return false;
    }
    BIGDATA_TS_OR_PRCSCOPE_S01 that = (BIGDATA_TS_OR_PRCSCOPE_S01) o;
    boolean equal = true;
    equal = equal && (this.用户号 == null ? that.用户号 == null : this.用户号.equals(that.用户号));
    equal = equal && (this.电价名称 == null ? that.电价名称 == null : this.电价名称.equals(that.电价名称));
    equal = equal && (this.是否分时 == null ? that.是否分时 == null : this.是否分时.equals(that.是否分时));
    equal = equal && (this.是否阶梯 == null ? that.是否阶梯 == null : this.是否阶梯.equals(that.是否阶梯));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.用户号 = JdbcWritableBridge.readString(1, __dbResults);
    this.电价名称 = JdbcWritableBridge.readString(2, __dbResults);
    this.是否分时 = JdbcWritableBridge.readString(3, __dbResults);
    this.是否阶梯 = JdbcWritableBridge.readString(4, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(用户号, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(电价名称, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(是否分时, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(是否阶梯, 4 + __off, 1, __dbStmt);
    return 4;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.用户号 = null;
    } else {
    this.用户号 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.电价名称 = null;
    } else {
    this.电价名称 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.是否分时 = null;
    } else {
    this.是否分时 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.是否阶梯 = null;
    } else {
    this.是否阶梯 = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.用户号) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 用户号);
    }
    if (null == this.电价名称) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 电价名称);
    }
    if (null == this.是否分时) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 是否分时);
    }
    if (null == this.是否阶梯) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, 是否阶梯);
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
    __sb.append(FieldFormatter.escapeAndEnclose(电价名称==null?"null":电价名称, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(是否分时==null?"null":是否分时, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(是否阶梯==null?"null":是否阶梯, delimiters));
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
    if (__cur_str.equals("null")) { this.电价名称 = null; } else {
      this.电价名称 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.是否分时 = null; } else {
      this.是否分时 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.是否阶梯 = null; } else {
      this.是否阶梯 = __cur_str;
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_TS_OR_PRCSCOPE_S01 o = (BIGDATA_TS_OR_PRCSCOPE_S01) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("用户号", this.用户号);
    __sqoop$field_map.put("电价名称", this.电价名称);
    __sqoop$field_map.put("是否分时", this.是否分时);
    __sqoop$field_map.put("是否阶梯", this.是否阶梯);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("用户号".equals(__fieldName)) {
      this.用户号 = (String) __fieldVal;
    }
    else    if ("电价名称".equals(__fieldName)) {
      this.电价名称 = (String) __fieldVal;
    }
    else    if ("是否分时".equals(__fieldName)) {
      this.是否分时 = (String) __fieldVal;
    }
    else    if ("是否阶梯".equals(__fieldName)) {
      this.是否阶梯 = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
