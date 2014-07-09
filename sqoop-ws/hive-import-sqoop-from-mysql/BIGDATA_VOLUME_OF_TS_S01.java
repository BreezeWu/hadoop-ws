// ORM class for table 'BIGDATA_VOLUME_OF_TS_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 22:04:06 CST 2014
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

public class BIGDATA_VOLUME_OF_TS_S01 extends SqoopRecord  implements DBWritable, Writable {
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
  public BIGDATA_VOLUME_OF_TS_S01 with_cons_no(String cons_no) {
    this.cons_no = cons_no;
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
  private Integer kwh_volume_top;
  public Integer get_kwh_volume_top() {
    return kwh_volume_top;
  }
  public void set_kwh_volume_top(Integer kwh_volume_top) {
    this.kwh_volume_top = kwh_volume_top;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_volume_top(Integer kwh_volume_top) {
    this.kwh_volume_top = kwh_volume_top;
    return this;
  }
  private Float kwh_prc_top;
  public Float get_kwh_prc_top() {
    return kwh_prc_top;
  }
  public void set_kwh_prc_top(Float kwh_prc_top) {
    this.kwh_prc_top = kwh_prc_top;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_prc_top(Float kwh_prc_top) {
    this.kwh_prc_top = kwh_prc_top;
    return this;
  }
  private Integer kwh_volume_high;
  public Integer get_kwh_volume_high() {
    return kwh_volume_high;
  }
  public void set_kwh_volume_high(Integer kwh_volume_high) {
    this.kwh_volume_high = kwh_volume_high;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_volume_high(Integer kwh_volume_high) {
    this.kwh_volume_high = kwh_volume_high;
    return this;
  }
  private Float kwh_prc_high;
  public Float get_kwh_prc_high() {
    return kwh_prc_high;
  }
  public void set_kwh_prc_high(Float kwh_prc_high) {
    this.kwh_prc_high = kwh_prc_high;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_prc_high(Float kwh_prc_high) {
    this.kwh_prc_high = kwh_prc_high;
    return this;
  }
  private Integer kwh_volume_mean;
  public Integer get_kwh_volume_mean() {
    return kwh_volume_mean;
  }
  public void set_kwh_volume_mean(Integer kwh_volume_mean) {
    this.kwh_volume_mean = kwh_volume_mean;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_volume_mean(Integer kwh_volume_mean) {
    this.kwh_volume_mean = kwh_volume_mean;
    return this;
  }
  private Float kwh_prc_mean;
  public Float get_kwh_prc_mean() {
    return kwh_prc_mean;
  }
  public void set_kwh_prc_mean(Float kwh_prc_mean) {
    this.kwh_prc_mean = kwh_prc_mean;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_prc_mean(Float kwh_prc_mean) {
    this.kwh_prc_mean = kwh_prc_mean;
    return this;
  }
  private Integer kwh_volume_low;
  public Integer get_kwh_volume_low() {
    return kwh_volume_low;
  }
  public void set_kwh_volume_low(Integer kwh_volume_low) {
    this.kwh_volume_low = kwh_volume_low;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_volume_low(Integer kwh_volume_low) {
    this.kwh_volume_low = kwh_volume_low;
    return this;
  }
  private Float kwh_prc_low;
  public Float get_kwh_prc_low() {
    return kwh_prc_low;
  }
  public void set_kwh_prc_low(Float kwh_prc_low) {
    this.kwh_prc_low = kwh_prc_low;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_prc_low(Float kwh_prc_low) {
    this.kwh_prc_low = kwh_prc_low;
    return this;
  }
  private Integer kwh_volume_bottom;
  public Integer get_kwh_volume_bottom() {
    return kwh_volume_bottom;
  }
  public void set_kwh_volume_bottom(Integer kwh_volume_bottom) {
    this.kwh_volume_bottom = kwh_volume_bottom;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_volume_bottom(Integer kwh_volume_bottom) {
    this.kwh_volume_bottom = kwh_volume_bottom;
    return this;
  }
  private Float kwh_prc_bottom;
  public Float get_kwh_prc_bottom() {
    return kwh_prc_bottom;
  }
  public void set_kwh_prc_bottom(Float kwh_prc_bottom) {
    this.kwh_prc_bottom = kwh_prc_bottom;
  }
  public BIGDATA_VOLUME_OF_TS_S01 with_kwh_prc_bottom(Float kwh_prc_bottom) {
    this.kwh_prc_bottom = kwh_prc_bottom;
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
    equal = equal && (this.cons_no == null ? that.cons_no == null : this.cons_no.equals(that.cons_no));
    equal = equal && (this.YM == null ? that.YM == null : this.YM.equals(that.YM));
    equal = equal && (this.kwh_volume_top == null ? that.kwh_volume_top == null : this.kwh_volume_top.equals(that.kwh_volume_top));
    equal = equal && (this.kwh_prc_top == null ? that.kwh_prc_top == null : this.kwh_prc_top.equals(that.kwh_prc_top));
    equal = equal && (this.kwh_volume_high == null ? that.kwh_volume_high == null : this.kwh_volume_high.equals(that.kwh_volume_high));
    equal = equal && (this.kwh_prc_high == null ? that.kwh_prc_high == null : this.kwh_prc_high.equals(that.kwh_prc_high));
    equal = equal && (this.kwh_volume_mean == null ? that.kwh_volume_mean == null : this.kwh_volume_mean.equals(that.kwh_volume_mean));
    equal = equal && (this.kwh_prc_mean == null ? that.kwh_prc_mean == null : this.kwh_prc_mean.equals(that.kwh_prc_mean));
    equal = equal && (this.kwh_volume_low == null ? that.kwh_volume_low == null : this.kwh_volume_low.equals(that.kwh_volume_low));
    equal = equal && (this.kwh_prc_low == null ? that.kwh_prc_low == null : this.kwh_prc_low.equals(that.kwh_prc_low));
    equal = equal && (this.kwh_volume_bottom == null ? that.kwh_volume_bottom == null : this.kwh_volume_bottom.equals(that.kwh_volume_bottom));
    equal = equal && (this.kwh_prc_bottom == null ? that.kwh_prc_bottom == null : this.kwh_prc_bottom.equals(that.kwh_prc_bottom));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.cons_no = JdbcWritableBridge.readString(1, __dbResults);
    this.YM = JdbcWritableBridge.readString(2, __dbResults);
    this.kwh_volume_top = JdbcWritableBridge.readInteger(3, __dbResults);
    this.kwh_prc_top = JdbcWritableBridge.readFloat(4, __dbResults);
    this.kwh_volume_high = JdbcWritableBridge.readInteger(5, __dbResults);
    this.kwh_prc_high = JdbcWritableBridge.readFloat(6, __dbResults);
    this.kwh_volume_mean = JdbcWritableBridge.readInteger(7, __dbResults);
    this.kwh_prc_mean = JdbcWritableBridge.readFloat(8, __dbResults);
    this.kwh_volume_low = JdbcWritableBridge.readInteger(9, __dbResults);
    this.kwh_prc_low = JdbcWritableBridge.readFloat(10, __dbResults);
    this.kwh_volume_bottom = JdbcWritableBridge.readInteger(11, __dbResults);
    this.kwh_prc_bottom = JdbcWritableBridge.readFloat(12, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(cons_no, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(YM, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(kwh_volume_top, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeFloat(kwh_prc_top, 4 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(kwh_volume_high, 5 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeFloat(kwh_prc_high, 6 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(kwh_volume_mean, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeFloat(kwh_prc_mean, 8 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(kwh_volume_low, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeFloat(kwh_prc_low, 10 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(kwh_volume_bottom, 11 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeFloat(kwh_prc_bottom, 12 + __off, 7, __dbStmt);
    return 12;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.cons_no = null;
    } else {
    this.cons_no = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YM = null;
    } else {
    this.YM = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_volume_top = null;
    } else {
    this.kwh_volume_top = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_prc_top = null;
    } else {
    this.kwh_prc_top = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_volume_high = null;
    } else {
    this.kwh_volume_high = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_prc_high = null;
    } else {
    this.kwh_prc_high = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_volume_mean = null;
    } else {
    this.kwh_volume_mean = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_prc_mean = null;
    } else {
    this.kwh_prc_mean = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_volume_low = null;
    } else {
    this.kwh_volume_low = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_prc_low = null;
    } else {
    this.kwh_prc_low = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_volume_bottom = null;
    } else {
    this.kwh_volume_bottom = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.kwh_prc_bottom = null;
    } else {
    this.kwh_prc_bottom = Float.valueOf(__dataIn.readFloat());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.cons_no) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cons_no);
    }
    if (null == this.YM) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, YM);
    }
    if (null == this.kwh_volume_top) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.kwh_volume_top);
    }
    if (null == this.kwh_prc_top) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.kwh_prc_top);
    }
    if (null == this.kwh_volume_high) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.kwh_volume_high);
    }
    if (null == this.kwh_prc_high) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.kwh_prc_high);
    }
    if (null == this.kwh_volume_mean) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.kwh_volume_mean);
    }
    if (null == this.kwh_prc_mean) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.kwh_prc_mean);
    }
    if (null == this.kwh_volume_low) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.kwh_volume_low);
    }
    if (null == this.kwh_prc_low) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.kwh_prc_low);
    }
    if (null == this.kwh_volume_bottom) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.kwh_volume_bottom);
    }
    if (null == this.kwh_prc_bottom) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.kwh_prc_bottom);
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
    __sb.append(FieldFormatter.escapeAndEnclose(YM==null?"null":YM, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_volume_top==null?"null":"" + kwh_volume_top, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_prc_top==null?"null":"" + kwh_prc_top, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_volume_high==null?"null":"" + kwh_volume_high, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_prc_high==null?"null":"" + kwh_prc_high, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_volume_mean==null?"null":"" + kwh_volume_mean, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_prc_mean==null?"null":"" + kwh_prc_mean, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_volume_low==null?"null":"" + kwh_volume_low, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_prc_low==null?"null":"" + kwh_prc_low, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_volume_bottom==null?"null":"" + kwh_volume_bottom, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(kwh_prc_bottom==null?"null":"" + kwh_prc_bottom, delimiters));
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
    if (__cur_str.equals("null")) { this.YM = null; } else {
      this.YM = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_volume_top = null; } else {
      this.kwh_volume_top = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_prc_top = null; } else {
      this.kwh_prc_top = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_volume_high = null; } else {
      this.kwh_volume_high = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_prc_high = null; } else {
      this.kwh_prc_high = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_volume_mean = null; } else {
      this.kwh_volume_mean = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_prc_mean = null; } else {
      this.kwh_prc_mean = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_volume_low = null; } else {
      this.kwh_volume_low = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_prc_low = null; } else {
      this.kwh_prc_low = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_volume_bottom = null; } else {
      this.kwh_volume_bottom = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.kwh_prc_bottom = null; } else {
      this.kwh_prc_bottom = Float.valueOf(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_VOLUME_OF_TS_S01 o = (BIGDATA_VOLUME_OF_TS_S01) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("cons_no", this.cons_no);
    __sqoop$field_map.put("YM", this.YM);
    __sqoop$field_map.put("kwh_volume_top", this.kwh_volume_top);
    __sqoop$field_map.put("kwh_prc_top", this.kwh_prc_top);
    __sqoop$field_map.put("kwh_volume_high", this.kwh_volume_high);
    __sqoop$field_map.put("kwh_prc_high", this.kwh_prc_high);
    __sqoop$field_map.put("kwh_volume_mean", this.kwh_volume_mean);
    __sqoop$field_map.put("kwh_prc_mean", this.kwh_prc_mean);
    __sqoop$field_map.put("kwh_volume_low", this.kwh_volume_low);
    __sqoop$field_map.put("kwh_prc_low", this.kwh_prc_low);
    __sqoop$field_map.put("kwh_volume_bottom", this.kwh_volume_bottom);
    __sqoop$field_map.put("kwh_prc_bottom", this.kwh_prc_bottom);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("cons_no".equals(__fieldName)) {
      this.cons_no = (String) __fieldVal;
    }
    else    if ("YM".equals(__fieldName)) {
      this.YM = (String) __fieldVal;
    }
    else    if ("kwh_volume_top".equals(__fieldName)) {
      this.kwh_volume_top = (Integer) __fieldVal;
    }
    else    if ("kwh_prc_top".equals(__fieldName)) {
      this.kwh_prc_top = (Float) __fieldVal;
    }
    else    if ("kwh_volume_high".equals(__fieldName)) {
      this.kwh_volume_high = (Integer) __fieldVal;
    }
    else    if ("kwh_prc_high".equals(__fieldName)) {
      this.kwh_prc_high = (Float) __fieldVal;
    }
    else    if ("kwh_volume_mean".equals(__fieldName)) {
      this.kwh_volume_mean = (Integer) __fieldVal;
    }
    else    if ("kwh_prc_mean".equals(__fieldName)) {
      this.kwh_prc_mean = (Float) __fieldVal;
    }
    else    if ("kwh_volume_low".equals(__fieldName)) {
      this.kwh_volume_low = (Integer) __fieldVal;
    }
    else    if ("kwh_prc_low".equals(__fieldName)) {
      this.kwh_prc_low = (Float) __fieldVal;
    }
    else    if ("kwh_volume_bottom".equals(__fieldName)) {
      this.kwh_volume_bottom = (Integer) __fieldVal;
    }
    else    if ("kwh_prc_bottom".equals(__fieldName)) {
      this.kwh_prc_bottom = (Float) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
