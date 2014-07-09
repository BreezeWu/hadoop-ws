// ORM class for table 'BIGDATA_ARC_VOLUME_PERM_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 21:51:47 CST 2014
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

public class BIGDATA_ARC_VOLUME_PERM_S01 extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Integer cons_id;
  public Integer get_cons_id() {
    return cons_id;
  }
  public void set_cons_id(Integer cons_id) {
    this.cons_id = cons_id;
  }
  public BIGDATA_ARC_VOLUME_PERM_S01 with_cons_id(Integer cons_id) {
    this.cons_id = cons_id;
    return this;
  }
  private String ym;
  public String get_ym() {
    return ym;
  }
  public void set_ym(String ym) {
    this.ym = ym;
  }
  public BIGDATA_ARC_VOLUME_PERM_S01 with_ym(String ym) {
    this.ym = ym;
    return this;
  }
  private Integer volume_per_month;
  public Integer get_volume_per_month() {
    return volume_per_month;
  }
  public void set_volume_per_month(Integer volume_per_month) {
    this.volume_per_month = volume_per_month;
  }
  public BIGDATA_ARC_VOLUME_PERM_S01 with_volume_per_month(Integer volume_per_month) {
    this.volume_per_month = volume_per_month;
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
    equal = equal && (this.cons_id == null ? that.cons_id == null : this.cons_id.equals(that.cons_id));
    equal = equal && (this.ym == null ? that.ym == null : this.ym.equals(that.ym));
    equal = equal && (this.volume_per_month == null ? that.volume_per_month == null : this.volume_per_month.equals(that.volume_per_month));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.cons_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.ym = JdbcWritableBridge.readString(2, __dbResults);
    this.volume_per_month = JdbcWritableBridge.readInteger(3, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(cons_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(ym, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(volume_per_month, 3 + __off, 4, __dbStmt);
    return 3;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.cons_id = null;
    } else {
    this.cons_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.ym = null;
    } else {
    this.ym = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.volume_per_month = null;
    } else {
    this.volume_per_month = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.cons_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.cons_id);
    }
    if (null == this.ym) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ym);
    }
    if (null == this.volume_per_month) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.volume_per_month);
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
    __sb.append(FieldFormatter.escapeAndEnclose(cons_id==null?"null":"" + cons_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ym==null?"null":ym, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(volume_per_month==null?"null":"" + volume_per_month, delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cons_id = null; } else {
      this.cons_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ym = null; } else {
      this.ym = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.volume_per_month = null; } else {
      this.volume_per_month = Integer.valueOf(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_ARC_VOLUME_PERM_S01 o = (BIGDATA_ARC_VOLUME_PERM_S01) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("cons_id", this.cons_id);
    __sqoop$field_map.put("ym", this.ym);
    __sqoop$field_map.put("volume_per_month", this.volume_per_month);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("cons_id".equals(__fieldName)) {
      this.cons_id = (Integer) __fieldVal;
    }
    else    if ("ym".equals(__fieldName)) {
      this.ym = (String) __fieldVal;
    }
    else    if ("volume_per_month".equals(__fieldName)) {
      this.volume_per_month = (Integer) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
