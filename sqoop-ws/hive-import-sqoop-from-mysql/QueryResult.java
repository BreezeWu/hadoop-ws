// ORM class for table 'null'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 09 20:34:27 CST 2014
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

public class QueryResult extends SqoopRecord  implements DBWritable, Writable {
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
  public QueryResult with_cons_id(Integer cons_id) {
    this.cons_id = cons_id;
    return this;
  }
  private String cons_name;
  public String get_cons_name() {
    return cons_name;
  }
  public void set_cons_name(String cons_name) {
    this.cons_name = cons_name;
  }
  public QueryResult with_cons_name(String cons_name) {
    this.cons_name = cons_name;
    return this;
  }
  private String prc_code;
  public String get_prc_code() {
    return prc_code;
  }
  public void set_prc_code(String prc_code) {
    this.prc_code = prc_code;
  }
  public QueryResult with_prc_code(String prc_code) {
    this.prc_code = prc_code;
    return this;
  }
  private String org_no;
  public String get_org_no() {
    return org_no;
  }
  public void set_org_no(String org_no) {
    this.org_no = org_no;
  }
  public QueryResult with_org_no(String org_no) {
    this.org_no = org_no;
    return this;
  }
  private String elec_type_code;
  public String get_elec_type_code() {
    return elec_type_code;
  }
  public void set_elec_type_code(String elec_type_code) {
    this.elec_type_code = elec_type_code;
  }
  public QueryResult with_elec_type_code(String elec_type_code) {
    this.elec_type_code = elec_type_code;
    return this;
  }
  private String volt_code;
  public String get_volt_code() {
    return volt_code;
  }
  public void set_volt_code(String volt_code) {
    this.volt_code = volt_code;
  }
  public QueryResult with_volt_code(String volt_code) {
    this.volt_code = volt_code;
    return this;
  }
  private Float contract_cap;
  public Float get_contract_cap() {
    return contract_cap;
  }
  public void set_contract_cap(Float contract_cap) {
    this.contract_cap = contract_cap;
  }
  public QueryResult with_contract_cap(Float contract_cap) {
    this.contract_cap = contract_cap;
    return this;
  }
  private String lode_attr_code;
  public String get_lode_attr_code() {
    return lode_attr_code;
  }
  public void set_lode_attr_code(String lode_attr_code) {
    this.lode_attr_code = lode_attr_code;
  }
  public QueryResult with_lode_attr_code(String lode_attr_code) {
    this.lode_attr_code = lode_attr_code;
    return this;
  }
  private String status_code;
  public String get_status_code() {
    return status_code;
  }
  public void set_status_code(String status_code) {
    this.status_code = status_code;
  }
  public QueryResult with_status_code(String status_code) {
    this.status_code = status_code;
    return this;
  }
  private String urban_rural_flag;
  public String get_urban_rural_flag() {
    return urban_rural_flag;
  }
  public void set_urban_rural_flag(String urban_rural_flag) {
    this.urban_rural_flag = urban_rural_flag;
  }
  public QueryResult with_urban_rural_flag(String urban_rural_flag) {
    this.urban_rural_flag = urban_rural_flag;
    return this;
  }
  private String trade_code;
  public String get_trade_code() {
    return trade_code;
  }
  public void set_trade_code(String trade_code) {
    this.trade_code = trade_code;
  }
  public QueryResult with_trade_code(String trade_code) {
    this.trade_code = trade_code;
    return this;
  }
  private String cust_type_code;
  public String get_cust_type_code() {
    return cust_type_code;
  }
  public void set_cust_type_code(String cust_type_code) {
    this.cust_type_code = cust_type_code;
  }
  public QueryResult with_cust_type_code(String cust_type_code) {
    this.cust_type_code = cust_type_code;
    return this;
  }
  private String cons_no;
  public String get_cons_no() {
    return cons_no;
  }
  public void set_cons_no(String cons_no) {
    this.cons_no = cons_no;
  }
  public QueryResult with_cons_no(String cons_no) {
    this.cons_no = cons_no;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof QueryResult)) {
      return false;
    }
    QueryResult that = (QueryResult) o;
    boolean equal = true;
    equal = equal && (this.cons_id == null ? that.cons_id == null : this.cons_id.equals(that.cons_id));
    equal = equal && (this.cons_name == null ? that.cons_name == null : this.cons_name.equals(that.cons_name));
    equal = equal && (this.prc_code == null ? that.prc_code == null : this.prc_code.equals(that.prc_code));
    equal = equal && (this.org_no == null ? that.org_no == null : this.org_no.equals(that.org_no));
    equal = equal && (this.elec_type_code == null ? that.elec_type_code == null : this.elec_type_code.equals(that.elec_type_code));
    equal = equal && (this.volt_code == null ? that.volt_code == null : this.volt_code.equals(that.volt_code));
    equal = equal && (this.contract_cap == null ? that.contract_cap == null : this.contract_cap.equals(that.contract_cap));
    equal = equal && (this.lode_attr_code == null ? that.lode_attr_code == null : this.lode_attr_code.equals(that.lode_attr_code));
    equal = equal && (this.status_code == null ? that.status_code == null : this.status_code.equals(that.status_code));
    equal = equal && (this.urban_rural_flag == null ? that.urban_rural_flag == null : this.urban_rural_flag.equals(that.urban_rural_flag));
    equal = equal && (this.trade_code == null ? that.trade_code == null : this.trade_code.equals(that.trade_code));
    equal = equal && (this.cust_type_code == null ? that.cust_type_code == null : this.cust_type_code.equals(that.cust_type_code));
    equal = equal && (this.cons_no == null ? that.cons_no == null : this.cons_no.equals(that.cons_no));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.cons_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.cons_name = JdbcWritableBridge.readString(2, __dbResults);
    this.prc_code = JdbcWritableBridge.readString(3, __dbResults);
    this.org_no = JdbcWritableBridge.readString(4, __dbResults);
    this.elec_type_code = JdbcWritableBridge.readString(5, __dbResults);
    this.volt_code = JdbcWritableBridge.readString(6, __dbResults);
    this.contract_cap = JdbcWritableBridge.readFloat(7, __dbResults);
    this.lode_attr_code = JdbcWritableBridge.readString(8, __dbResults);
    this.status_code = JdbcWritableBridge.readString(9, __dbResults);
    this.urban_rural_flag = JdbcWritableBridge.readString(10, __dbResults);
    this.trade_code = JdbcWritableBridge.readString(11, __dbResults);
    this.cust_type_code = JdbcWritableBridge.readString(12, __dbResults);
    this.cons_no = JdbcWritableBridge.readString(13, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(cons_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(cons_name, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(prc_code, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(org_no, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(elec_type_code, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(volt_code, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(contract_cap, 7 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeString(lode_attr_code, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(status_code, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(urban_rural_flag, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(trade_code, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cust_type_code, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(cons_no, 13 + __off, 12, __dbStmt);
    return 13;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.cons_id = null;
    } else {
    this.cons_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.cons_name = null;
    } else {
    this.cons_name = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.prc_code = null;
    } else {
    this.prc_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.org_no = null;
    } else {
    this.org_no = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.elec_type_code = null;
    } else {
    this.elec_type_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.volt_code = null;
    } else {
    this.volt_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.contract_cap = null;
    } else {
    this.contract_cap = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.lode_attr_code = null;
    } else {
    this.lode_attr_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.status_code = null;
    } else {
    this.status_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.urban_rural_flag = null;
    } else {
    this.urban_rural_flag = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.trade_code = null;
    } else {
    this.trade_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cust_type_code = null;
    } else {
    this.cust_type_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cons_no = null;
    } else {
    this.cons_no = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.cons_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.cons_id);
    }
    if (null == this.cons_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cons_name);
    }
    if (null == this.prc_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, prc_code);
    }
    if (null == this.org_no) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, org_no);
    }
    if (null == this.elec_type_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, elec_type_code);
    }
    if (null == this.volt_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, volt_code);
    }
    if (null == this.contract_cap) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.contract_cap);
    }
    if (null == this.lode_attr_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, lode_attr_code);
    }
    if (null == this.status_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, status_code);
    }
    if (null == this.urban_rural_flag) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, urban_rural_flag);
    }
    if (null == this.trade_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, trade_code);
    }
    if (null == this.cust_type_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cust_type_code);
    }
    if (null == this.cons_no) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, cons_no);
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
    __sb.append(FieldFormatter.escapeAndEnclose(cons_name==null?"null":cons_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(prc_code==null?"null":prc_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(org_no==null?"null":org_no, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(elec_type_code==null?"null":elec_type_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(volt_code==null?"null":volt_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(contract_cap==null?"null":"" + contract_cap, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lode_attr_code==null?"null":lode_attr_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(status_code==null?"null":status_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(urban_rural_flag==null?"null":urban_rural_flag, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(trade_code==null?"null":trade_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cust_type_code==null?"null":cust_type_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cons_no==null?"null":cons_no, delimiters));
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
    if (__cur_str.equals("null")) { this.cons_name = null; } else {
      this.cons_name = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.prc_code = null; } else {
      this.prc_code = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.org_no = null; } else {
      this.org_no = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.elec_type_code = null; } else {
      this.elec_type_code = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.volt_code = null; } else {
      this.volt_code = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.contract_cap = null; } else {
      this.contract_cap = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.lode_attr_code = null; } else {
      this.lode_attr_code = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.status_code = null; } else {
      this.status_code = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.urban_rural_flag = null; } else {
      this.urban_rural_flag = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.trade_code = null; } else {
      this.trade_code = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.cust_type_code = null; } else {
      this.cust_type_code = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.cons_no = null; } else {
      this.cons_no = __cur_str;
    }

  }

  public Object clone() throws CloneNotSupportedException {
    QueryResult o = (QueryResult) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("cons_id", this.cons_id);
    __sqoop$field_map.put("cons_name", this.cons_name);
    __sqoop$field_map.put("prc_code", this.prc_code);
    __sqoop$field_map.put("org_no", this.org_no);
    __sqoop$field_map.put("elec_type_code", this.elec_type_code);
    __sqoop$field_map.put("volt_code", this.volt_code);
    __sqoop$field_map.put("contract_cap", this.contract_cap);
    __sqoop$field_map.put("lode_attr_code", this.lode_attr_code);
    __sqoop$field_map.put("status_code", this.status_code);
    __sqoop$field_map.put("urban_rural_flag", this.urban_rural_flag);
    __sqoop$field_map.put("trade_code", this.trade_code);
    __sqoop$field_map.put("cust_type_code", this.cust_type_code);
    __sqoop$field_map.put("cons_no", this.cons_no);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("cons_id".equals(__fieldName)) {
      this.cons_id = (Integer) __fieldVal;
    }
    else    if ("cons_name".equals(__fieldName)) {
      this.cons_name = (String) __fieldVal;
    }
    else    if ("prc_code".equals(__fieldName)) {
      this.prc_code = (String) __fieldVal;
    }
    else    if ("org_no".equals(__fieldName)) {
      this.org_no = (String) __fieldVal;
    }
    else    if ("elec_type_code".equals(__fieldName)) {
      this.elec_type_code = (String) __fieldVal;
    }
    else    if ("volt_code".equals(__fieldName)) {
      this.volt_code = (String) __fieldVal;
    }
    else    if ("contract_cap".equals(__fieldName)) {
      this.contract_cap = (Float) __fieldVal;
    }
    else    if ("lode_attr_code".equals(__fieldName)) {
      this.lode_attr_code = (String) __fieldVal;
    }
    else    if ("status_code".equals(__fieldName)) {
      this.status_code = (String) __fieldVal;
    }
    else    if ("urban_rural_flag".equals(__fieldName)) {
      this.urban_rural_flag = (String) __fieldVal;
    }
    else    if ("trade_code".equals(__fieldName)) {
      this.trade_code = (String) __fieldVal;
    }
    else    if ("cust_type_code".equals(__fieldName)) {
      this.cust_type_code = (String) __fieldVal;
    }
    else    if ("cons_no".equals(__fieldName)) {
      this.cons_no = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
