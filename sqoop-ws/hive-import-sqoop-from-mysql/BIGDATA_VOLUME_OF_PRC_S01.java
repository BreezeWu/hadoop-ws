// ORM class for table 'BIGDATA_VOLUME_OF_PRC_S01'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 22:04:43 CST 2014
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

public class BIGDATA_VOLUME_OF_PRC_S01 extends SqoopRecord  implements DBWritable, Writable {
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
  public BIGDATA_VOLUME_OF_PRC_S01 with_CONS_NO(String CONS_NO) {
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
  public BIGDATA_VOLUME_OF_PRC_S01 with_YM(String YM) {
    this.YM = YM;
    return this;
  }
  private Integer u_pq_1;
  public Integer get_u_pq_1() {
    return u_pq_1;
  }
  public void set_u_pq_1(Integer u_pq_1) {
    this.u_pq_1 = u_pq_1;
  }
  public BIGDATA_VOLUME_OF_PRC_S01 with_u_pq_1(Integer u_pq_1) {
    this.u_pq_1 = u_pq_1;
    return this;
  }
  private Float prc_u_pq_1;
  public Float get_prc_u_pq_1() {
    return prc_u_pq_1;
  }
  public void set_prc_u_pq_1(Float prc_u_pq_1) {
    this.prc_u_pq_1 = prc_u_pq_1;
  }
  public BIGDATA_VOLUME_OF_PRC_S01 with_prc_u_pq_1(Float prc_u_pq_1) {
    this.prc_u_pq_1 = prc_u_pq_1;
    return this;
  }
  private Integer u_pq_2;
  public Integer get_u_pq_2() {
    return u_pq_2;
  }
  public void set_u_pq_2(Integer u_pq_2) {
    this.u_pq_2 = u_pq_2;
  }
  public BIGDATA_VOLUME_OF_PRC_S01 with_u_pq_2(Integer u_pq_2) {
    this.u_pq_2 = u_pq_2;
    return this;
  }
  private Float prc_u_pq_2;
  public Float get_prc_u_pq_2() {
    return prc_u_pq_2;
  }
  public void set_prc_u_pq_2(Float prc_u_pq_2) {
    this.prc_u_pq_2 = prc_u_pq_2;
  }
  public BIGDATA_VOLUME_OF_PRC_S01 with_prc_u_pq_2(Float prc_u_pq_2) {
    this.prc_u_pq_2 = prc_u_pq_2;
    return this;
  }
  private Integer u_pq_3;
  public Integer get_u_pq_3() {
    return u_pq_3;
  }
  public void set_u_pq_3(Integer u_pq_3) {
    this.u_pq_3 = u_pq_3;
  }
  public BIGDATA_VOLUME_OF_PRC_S01 with_u_pq_3(Integer u_pq_3) {
    this.u_pq_3 = u_pq_3;
    return this;
  }
  private Float prc_u_pq_4;
  public Float get_prc_u_pq_4() {
    return prc_u_pq_4;
  }
  public void set_prc_u_pq_4(Float prc_u_pq_4) {
    this.prc_u_pq_4 = prc_u_pq_4;
  }
  public BIGDATA_VOLUME_OF_PRC_S01 with_prc_u_pq_4(Float prc_u_pq_4) {
    this.prc_u_pq_4 = prc_u_pq_4;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BIGDATA_VOLUME_OF_PRC_S01)) {
      return false;
    }
    BIGDATA_VOLUME_OF_PRC_S01 that = (BIGDATA_VOLUME_OF_PRC_S01) o;
    boolean equal = true;
    equal = equal && (this.CONS_NO == null ? that.CONS_NO == null : this.CONS_NO.equals(that.CONS_NO));
    equal = equal && (this.YM == null ? that.YM == null : this.YM.equals(that.YM));
    equal = equal && (this.u_pq_1 == null ? that.u_pq_1 == null : this.u_pq_1.equals(that.u_pq_1));
    equal = equal && (this.prc_u_pq_1 == null ? that.prc_u_pq_1 == null : this.prc_u_pq_1.equals(that.prc_u_pq_1));
    equal = equal && (this.u_pq_2 == null ? that.u_pq_2 == null : this.u_pq_2.equals(that.u_pq_2));
    equal = equal && (this.prc_u_pq_2 == null ? that.prc_u_pq_2 == null : this.prc_u_pq_2.equals(that.prc_u_pq_2));
    equal = equal && (this.u_pq_3 == null ? that.u_pq_3 == null : this.u_pq_3.equals(that.u_pq_3));
    equal = equal && (this.prc_u_pq_4 == null ? that.prc_u_pq_4 == null : this.prc_u_pq_4.equals(that.prc_u_pq_4));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.CONS_NO = JdbcWritableBridge.readString(1, __dbResults);
    this.YM = JdbcWritableBridge.readString(2, __dbResults);
    this.u_pq_1 = JdbcWritableBridge.readInteger(3, __dbResults);
    this.prc_u_pq_1 = JdbcWritableBridge.readFloat(4, __dbResults);
    this.u_pq_2 = JdbcWritableBridge.readInteger(5, __dbResults);
    this.prc_u_pq_2 = JdbcWritableBridge.readFloat(6, __dbResults);
    this.u_pq_3 = JdbcWritableBridge.readInteger(7, __dbResults);
    this.prc_u_pq_4 = JdbcWritableBridge.readFloat(8, __dbResults);
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
    JdbcWritableBridge.writeInteger(u_pq_1, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeFloat(prc_u_pq_1, 4 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(u_pq_2, 5 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeFloat(prc_u_pq_2, 6 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(u_pq_3, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeFloat(prc_u_pq_4, 8 + __off, 7, __dbStmt);
    return 8;
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
        this.u_pq_1 = null;
    } else {
    this.u_pq_1 = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.prc_u_pq_1 = null;
    } else {
    this.prc_u_pq_1 = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.u_pq_2 = null;
    } else {
    this.u_pq_2 = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.prc_u_pq_2 = null;
    } else {
    this.prc_u_pq_2 = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.u_pq_3 = null;
    } else {
    this.u_pq_3 = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.prc_u_pq_4 = null;
    } else {
    this.prc_u_pq_4 = Float.valueOf(__dataIn.readFloat());
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
    if (null == this.u_pq_1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.u_pq_1);
    }
    if (null == this.prc_u_pq_1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.prc_u_pq_1);
    }
    if (null == this.u_pq_2) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.u_pq_2);
    }
    if (null == this.prc_u_pq_2) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.prc_u_pq_2);
    }
    if (null == this.u_pq_3) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.u_pq_3);
    }
    if (null == this.prc_u_pq_4) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.prc_u_pq_4);
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
    __sb.append(FieldFormatter.escapeAndEnclose(u_pq_1==null?"null":"" + u_pq_1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(prc_u_pq_1==null?"null":"" + prc_u_pq_1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(u_pq_2==null?"null":"" + u_pq_2, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(prc_u_pq_2==null?"null":"" + prc_u_pq_2, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(u_pq_3==null?"null":"" + u_pq_3, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(prc_u_pq_4==null?"null":"" + prc_u_pq_4, delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.u_pq_1 = null; } else {
      this.u_pq_1 = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.prc_u_pq_1 = null; } else {
      this.prc_u_pq_1 = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.u_pq_2 = null; } else {
      this.u_pq_2 = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.prc_u_pq_2 = null; } else {
      this.prc_u_pq_2 = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.u_pq_3 = null; } else {
      this.u_pq_3 = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.prc_u_pq_4 = null; } else {
      this.prc_u_pq_4 = Float.valueOf(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    BIGDATA_VOLUME_OF_PRC_S01 o = (BIGDATA_VOLUME_OF_PRC_S01) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("CONS_NO", this.CONS_NO);
    __sqoop$field_map.put("YM", this.YM);
    __sqoop$field_map.put("u_pq_1", this.u_pq_1);
    __sqoop$field_map.put("prc_u_pq_1", this.prc_u_pq_1);
    __sqoop$field_map.put("u_pq_2", this.u_pq_2);
    __sqoop$field_map.put("prc_u_pq_2", this.prc_u_pq_2);
    __sqoop$field_map.put("u_pq_3", this.u_pq_3);
    __sqoop$field_map.put("prc_u_pq_4", this.prc_u_pq_4);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("CONS_NO".equals(__fieldName)) {
      this.CONS_NO = (String) __fieldVal;
    }
    else    if ("YM".equals(__fieldName)) {
      this.YM = (String) __fieldVal;
    }
    else    if ("u_pq_1".equals(__fieldName)) {
      this.u_pq_1 = (Integer) __fieldVal;
    }
    else    if ("prc_u_pq_1".equals(__fieldName)) {
      this.prc_u_pq_1 = (Float) __fieldVal;
    }
    else    if ("u_pq_2".equals(__fieldName)) {
      this.u_pq_2 = (Integer) __fieldVal;
    }
    else    if ("prc_u_pq_2".equals(__fieldName)) {
      this.prc_u_pq_2 = (Float) __fieldVal;
    }
    else    if ("u_pq_3".equals(__fieldName)) {
      this.u_pq_3 = (Integer) __fieldVal;
    }
    else    if ("prc_u_pq_4".equals(__fieldName)) {
      this.prc_u_pq_4 = (Float) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
