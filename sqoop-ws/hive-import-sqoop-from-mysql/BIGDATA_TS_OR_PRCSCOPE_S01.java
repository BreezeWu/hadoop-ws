// ORM class for table 'BIGDATA_TS_OR_PRCSCOPE_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Thu Jul 17 10:11:59 CST 2014
// For connector: org.apache.sqoop.manager.MySQLManager
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
  private String cons_no;
  public String get_cons_no() {
    return cons_no;
  }
  public void set_cons_no(String cons_no) {
    this.cons_no = cons_no;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S01 with_cons_no(String cons_no) {
    this.cons_no = cons_no;
    return this;
  }
  private String prc_code;
  public String get_prc_code() {
    return prc_code;
  }
  public void set_prc_code(String prc_code) {
    this.prc_code = prc_code;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S01 with_prc_code(String prc_code) {
    this.prc_code = prc_code;
    return this;
  }
  private String ts_flag;
  public String get_ts_flag() {
    return ts_flag;
  }
  public void set_ts_flag(String ts_flag) {
    this.ts_flag = ts_flag;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S01 with_ts_flag(String ts_flag) {
    this.ts_flag = ts_flag;
    return this;
  }
  private String ladder_flag;
  public String get_ladder_flag() {
    return ladder_flag;
  }
  public void set_ladder_flag(String ladder_flag) {
    this.ladder_flag = ladder_flag;
  }
  public BIGDATA_TS_OR_PRCSCOPE_S01 with_ladder_flag(String ladder_flag) {
    this.ladder_flag = ladder_flag;
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
    equal = equal && (this.cons_no == null ? that.cons_no == null : this.cons_no.equals(that.cons_no));
    equal = equal && (this.prc_code == null ? that.prc_code == null : this.prc_code.equals(that.prc_code));
    equal = equal && (this.ts_flag == null ? that.ts_flag == null : this.ts_flag.equals(that.ts_flag));
    equal = equal && (this.ladder_flag == null ? that.ladder_flag == null : this.ladder_flag.equals(that.ladder_flag));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.cons_no = JdbcWritableBridge.readString(1, __dbResults);
    this.prc_code = JdbcWritableBridge.readString(2, __dbResults);
    this.ts_flag = JdbcWritableBridge.readString(3, __dbResults);
    this.ladder_flag = JdbcWritableBridge.readString(4, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(cons_no, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(prc_code, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ts_flag, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ladder_flag, 4 + __off, 12, __dbStmt);
    return 4;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.cons_no = null;
    } else {
    this.cons_no = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.prc_code = null;
    } else {
    this.prc_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ts_flag = null;
    } else {
    this.ts_flag = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ladder_flag = null;
    } else {
    this.ladder_flag = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.cons_no) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cons_no);
    }
    if (null == this.prc_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, prc_code);
    }
    if (null == this.ts_flag) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ts_flag);
    }
    if (null == this.ladder_flag) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ladder_flag);
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
    __sb.append(FieldFormatter.escapeAndEnclose(cons_no==null?"null":cons_no, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(prc_code==null?"null":prc_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ts_flag==null?"null":ts_flag, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ladder_flag==null?"null":ladder_flag, delimiters));
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
    if (__cur_str.equals("null")) { this.cons_no = null; } else {
      this.cons_no = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.prc_code = null; } else {
      this.prc_code = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ts_flag = null; } else {
      this.ts_flag = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ladder_flag = null; } else {
      this.ladder_flag = __cur_str;
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_TS_OR_PRCSCOPE_S01 o = (BIGDATA_TS_OR_PRCSCOPE_S01) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("cons_no", this.cons_no);
    __sqoop$field_map.put("prc_code", this.prc_code);
    __sqoop$field_map.put("ts_flag", this.ts_flag);
    __sqoop$field_map.put("ladder_flag", this.ladder_flag);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("cons_no".equals(__fieldName)) {
      this.cons_no = (String) __fieldVal;
    }
    else    if ("prc_code".equals(__fieldName)) {
      this.prc_code = (String) __fieldVal;
    }
    else    if ("ts_flag".equals(__fieldName)) {
      this.ts_flag = (String) __fieldVal;
    }
    else    if ("ladder_flag".equals(__fieldName)) {
      this.ladder_flag = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
