// ORM class for table 'BIGDATA_RCVBL_FLOW_PM_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 21:53:32 CST 2014
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

public class BIGDATA_RCVBL_FLOW_PM_S01 extends SqoopRecord  implements DBWritable, Writable {
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
  public BIGDATA_RCVBL_FLOW_PM_S01 with_cons_no(String cons_no) {
    this.cons_no = cons_no;
    return this;
  }
  private String ym;
  public String get_ym() {
    return ym;
  }
  public void set_ym(String ym) {
    this.ym = ym;
  }
  public BIGDATA_RCVBL_FLOW_PM_S01 with_ym(String ym) {
    this.ym = ym;
    return this;
  }
  private Float rcved_amt;
  public Float get_rcved_amt() {
    return rcved_amt;
  }
  public void set_rcved_amt(Float rcved_amt) {
    this.rcved_amt = rcved_amt;
  }
  public BIGDATA_RCVBL_FLOW_PM_S01 with_rcved_amt(Float rcved_amt) {
    this.rcved_amt = rcved_amt;
    return this;
  }
  private Float rcvbl_amt;
  public Float get_rcvbl_amt() {
    return rcvbl_amt;
  }
  public void set_rcvbl_amt(Float rcvbl_amt) {
    this.rcvbl_amt = rcvbl_amt;
  }
  public BIGDATA_RCVBL_FLOW_PM_S01 with_rcvbl_amt(Float rcvbl_amt) {
    this.rcvbl_amt = rcvbl_amt;
    return this;
  }
  private Float owning_amt;
  public Float get_owning_amt() {
    return owning_amt;
  }
  public void set_owning_amt(Float owning_amt) {
    this.owning_amt = owning_amt;
  }
  public BIGDATA_RCVBL_FLOW_PM_S01 with_owning_amt(Float owning_amt) {
    this.owning_amt = owning_amt;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BIGDATA_RCVBL_FLOW_PM_S01)) {
      return false;
    }
    BIGDATA_RCVBL_FLOW_PM_S01 that = (BIGDATA_RCVBL_FLOW_PM_S01) o;
    boolean equal = true;
    equal = equal && (this.cons_no == null ? that.cons_no == null : this.cons_no.equals(that.cons_no));
    equal = equal && (this.ym == null ? that.ym == null : this.ym.equals(that.ym));
    equal = equal && (this.rcved_amt == null ? that.rcved_amt == null : this.rcved_amt.equals(that.rcved_amt));
    equal = equal && (this.rcvbl_amt == null ? that.rcvbl_amt == null : this.rcvbl_amt.equals(that.rcvbl_amt));
    equal = equal && (this.owning_amt == null ? that.owning_amt == null : this.owning_amt.equals(that.owning_amt));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.cons_no = JdbcWritableBridge.readString(1, __dbResults);
    this.ym = JdbcWritableBridge.readString(2, __dbResults);
    this.rcved_amt = JdbcWritableBridge.readFloat(3, __dbResults);
    this.rcvbl_amt = JdbcWritableBridge.readFloat(4, __dbResults);
    this.owning_amt = JdbcWritableBridge.readFloat(5, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(cons_no, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ym, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(rcved_amt, 3 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(rcvbl_amt, 4 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(owning_amt, 5 + __off, 7, __dbStmt);
    return 5;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.cons_no = null;
    } else {
    this.cons_no = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ym = null;
    } else {
    this.ym = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.rcved_amt = null;
    } else {
    this.rcved_amt = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.rcvbl_amt = null;
    } else {
    this.rcvbl_amt = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.owning_amt = null;
    } else {
    this.owning_amt = Float.valueOf(__dataIn.readFloat());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.cons_no) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cons_no);
    }
    if (null == this.ym) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ym);
    }
    if (null == this.rcved_amt) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.rcved_amt);
    }
    if (null == this.rcvbl_amt) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.rcvbl_amt);
    }
    if (null == this.owning_amt) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.owning_amt);
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
    __sb.append(FieldFormatter.escapeAndEnclose(ym==null?"null":ym, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rcved_amt==null?"null":"" + rcved_amt, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rcvbl_amt==null?"null":"" + rcvbl_amt, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(owning_amt==null?"null":"" + owning_amt, delimiters));
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
    if (__cur_str.equals("null")) { this.ym = null; } else {
      this.ym = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rcved_amt = null; } else {
      this.rcved_amt = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rcvbl_amt = null; } else {
      this.rcvbl_amt = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.owning_amt = null; } else {
      this.owning_amt = Float.valueOf(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_RCVBL_FLOW_PM_S01 o = (BIGDATA_RCVBL_FLOW_PM_S01) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("cons_no", this.cons_no);
    __sqoop$field_map.put("ym", this.ym);
    __sqoop$field_map.put("rcved_amt", this.rcved_amt);
    __sqoop$field_map.put("rcvbl_amt", this.rcvbl_amt);
    __sqoop$field_map.put("owning_amt", this.owning_amt);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("cons_no".equals(__fieldName)) {
      this.cons_no = (String) __fieldVal;
    }
    else    if ("ym".equals(__fieldName)) {
      this.ym = (String) __fieldVal;
    }
    else    if ("rcved_amt".equals(__fieldName)) {
      this.rcved_amt = (Float) __fieldVal;
    }
    else    if ("rcvbl_amt".equals(__fieldName)) {
      this.rcvbl_amt = (Float) __fieldVal;
    }
    else    if ("owning_amt".equals(__fieldName)) {
      this.owning_amt = (Float) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
