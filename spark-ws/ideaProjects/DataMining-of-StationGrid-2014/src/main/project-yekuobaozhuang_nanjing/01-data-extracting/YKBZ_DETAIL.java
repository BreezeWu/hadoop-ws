// ORM class for table 'YKBZ_DETAIL'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Dec 03 15:45:46 CST 2014
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

public class YKBZ_DETAIL extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private String YEKUOGDH;
  public String get_YEKUOGDH() {
    return YEKUOGDH;
  }
  public void set_YEKUOGDH(String YEKUOGDH) {
    this.YEKUOGDH = YEKUOGDH;
  }
  public YKBZ_DETAIL with_YEKUOGDH(String YEKUOGDH) {
    this.YEKUOGDH = YEKUOGDH;
    return this;
  }
  private java.sql.Timestamp GONGDANCJRQ;
  public java.sql.Timestamp get_GONGDANCJRQ() {
    return GONGDANCJRQ;
  }
  public void set_GONGDANCJRQ(java.sql.Timestamp GONGDANCJRQ) {
    this.GONGDANCJRQ = GONGDANCJRQ;
  }
  public YKBZ_DETAIL with_GONGDANCJRQ(java.sql.Timestamp GONGDANCJRQ) {
    this.GONGDANCJRQ = GONGDANCJRQ;
    return this;
  }
  private String GONGDIANDW_DS;
  public String get_GONGDIANDW_DS() {
    return GONGDIANDW_DS;
  }
  public void set_GONGDIANDW_DS(String GONGDIANDW_DS) {
    this.GONGDIANDW_DS = GONGDIANDW_DS;
  }
  public YKBZ_DETAIL with_GONGDIANDW_DS(String GONGDIANDW_DS) {
    this.GONGDIANDW_DS = GONGDIANDW_DS;
    return this;
  }
  private String GONGDIANDW_XQ;
  public String get_GONGDIANDW_XQ() {
    return GONGDIANDW_XQ;
  }
  public void set_GONGDIANDW_XQ(String GONGDIANDW_XQ) {
    this.GONGDIANDW_XQ = GONGDIANDW_XQ;
  }
  public YKBZ_DETAIL with_GONGDIANDW_XQ(String GONGDIANDW_XQ) {
    this.GONGDIANDW_XQ = GONGDIANDW_XQ;
    return this;
  }
  private String YONGHUMC;
  public String get_YONGHUMC() {
    return YONGHUMC;
  }
  public void set_YONGHUMC(String YONGHUMC) {
    this.YONGHUMC = YONGHUMC;
  }
  public YKBZ_DETAIL with_YONGHUMC(String YONGHUMC) {
    this.YONGHUMC = YONGHUMC;
    return this;
  }
  private java.math.BigDecimal YONGHUBZRL;
  public java.math.BigDecimal get_YONGHUBZRL() {
    return YONGHUBZRL;
  }
  public void set_YONGHUBZRL(java.math.BigDecimal YONGHUBZRL) {
    this.YONGHUBZRL = YONGHUBZRL;
  }
  public YKBZ_DETAIL with_YONGHUBZRL(java.math.BigDecimal YONGHUBZRL) {
    this.YONGHUBZRL = YONGHUBZRL;
    return this;
  }
  private java.math.BigDecimal YONGHUYYRL;
  public java.math.BigDecimal get_YONGHUYYRL() {
    return YONGHUYYRL;
  }
  public void set_YONGHUYYRL(java.math.BigDecimal YONGHUYYRL) {
    this.YONGHUYYRL = YONGHUYYRL;
  }
  public YKBZ_DETAIL with_YONGHUYYRL(java.math.BigDecimal YONGHUYYRL) {
    this.YONGHUYYRL = YONGHUYYRL;
    return this;
  }
  private String DIANYADJ;
  public String get_DIANYADJ() {
    return DIANYADJ;
  }
  public void set_DIANYADJ(String DIANYADJ) {
    this.DIANYADJ = DIANYADJ;
  }
  public YKBZ_DETAIL with_DIANYADJ(String DIANYADJ) {
    this.DIANYADJ = DIANYADJ;
    return this;
  }
  private String YONGDIANLB;
  public String get_YONGDIANLB() {
    return YONGDIANLB;
  }
  public void set_YONGDIANLB(String YONGDIANLB) {
    this.YONGDIANLB = YONGDIANLB;
  }
  public YKBZ_DETAIL with_YONGDIANLB(String YONGDIANLB) {
    this.YONGDIANLB = YONGDIANLB;
    return this;
  }
  private String YONGHUXY;
  public String get_YONGHUXY() {
    return YONGHUXY;
  }
  public void set_YONGHUXY(String YONGHUXY) {
    this.YONGHUXY = YONGHUXY;
  }
  public YKBZ_DETAIL with_YONGHUXY(String YONGHUXY) {
    this.YONGHUXY = YONGHUXY;
    return this;
  }
  private String SHIFOUYJRGC;
  public String get_SHIFOUYJRGC() {
    return SHIFOUYJRGC;
  }
  public void set_SHIFOUYJRGC(String SHIFOUYJRGC) {
    this.SHIFOUYJRGC = SHIFOUYJRGC;
  }
  public YKBZ_DETAIL with_SHIFOUYJRGC(String SHIFOUYJRGC) {
    this.SHIFOUYJRGC = SHIFOUYJRGC;
    return this;
  }
  private String DANSHUANGDY;
  public String get_DANSHUANGDY() {
    return DANSHUANGDY;
  }
  public void set_DANSHUANGDY(String DANSHUANGDY) {
    this.DANSHUANGDY = DANSHUANGDY;
  }
  public YKBZ_DETAIL with_DANSHUANGDY(String DANSHUANGDY) {
    this.DANSHUANGDY = DANSHUANGDY;
    return this;
  }
  private java.sql.Timestamp YEWUSLJSSJ;
  public java.sql.Timestamp get_YEWUSLJSSJ() {
    return YEWUSLJSSJ;
  }
  public void set_YEWUSLJSSJ(java.sql.Timestamp YEWUSLJSSJ) {
    this.YEWUSLJSSJ = YEWUSLJSSJ;
  }
  public YKBZ_DETAIL with_YEWUSLJSSJ(java.sql.Timestamp YEWUSLJSSJ) {
    this.YEWUSLJSSJ = YEWUSLJSSJ;
    return this;
  }
  private java.sql.Timestamp YEKUOSLSJ;
  public java.sql.Timestamp get_YEKUOSLSJ() {
    return YEKUOSLSJ;
  }
  public void set_YEKUOSLSJ(java.sql.Timestamp YEKUOSLSJ) {
    this.YEKUOSLSJ = YEKUOSLSJ;
  }
  public YKBZ_DETAIL with_YEKUOSLSJ(java.sql.Timestamp YEKUOSLSJ) {
    this.YEKUOSLSJ = YEKUOSLSJ;
    return this;
  }
  private java.sql.Timestamp XIANCHANGKCKSSJ;
  public java.sql.Timestamp get_XIANCHANGKCKSSJ() {
    return XIANCHANGKCKSSJ;
  }
  public void set_XIANCHANGKCKSSJ(java.sql.Timestamp XIANCHANGKCKSSJ) {
    this.XIANCHANGKCKSSJ = XIANCHANGKCKSSJ;
  }
  public YKBZ_DETAIL with_XIANCHANGKCKSSJ(java.sql.Timestamp XIANCHANGKCKSSJ) {
    this.XIANCHANGKCKSSJ = XIANCHANGKCKSSJ;
    return this;
  }
  private java.sql.Timestamp XIANCHANGKCJSSJ;
  public java.sql.Timestamp get_XIANCHANGKCJSSJ() {
    return XIANCHANGKCJSSJ;
  }
  public void set_XIANCHANGKCJSSJ(java.sql.Timestamp XIANCHANGKCJSSJ) {
    this.XIANCHANGKCJSSJ = XIANCHANGKCJSSJ;
  }
  public YKBZ_DETAIL with_XIANCHANGKCJSSJ(java.sql.Timestamp XIANCHANGKCJSSJ) {
    this.XIANCHANGKCJSSJ = XIANCHANGKCJSSJ;
    return this;
  }
  private java.math.BigDecimal XIANCHANGKCSC;
  public java.math.BigDecimal get_XIANCHANGKCSC() {
    return XIANCHANGKCSC;
  }
  public void set_XIANCHANGKCSC(java.math.BigDecimal XIANCHANGKCSC) {
    this.XIANCHANGKCSC = XIANCHANGKCSC;
  }
  public YKBZ_DETAIL with_XIANCHANGKCSC(java.math.BigDecimal XIANCHANGKCSC) {
    this.XIANCHANGKCSC = XIANCHANGKCSC;
    return this;
  }
  private java.sql.Timestamp GONGDIANFASCKSSJ;
  public java.sql.Timestamp get_GONGDIANFASCKSSJ() {
    return GONGDIANFASCKSSJ;
  }
  public void set_GONGDIANFASCKSSJ(java.sql.Timestamp GONGDIANFASCKSSJ) {
    this.GONGDIANFASCKSSJ = GONGDIANFASCKSSJ;
  }
  public YKBZ_DETAIL with_GONGDIANFASCKSSJ(java.sql.Timestamp GONGDIANFASCKSSJ) {
    this.GONGDIANFASCKSSJ = GONGDIANFASCKSSJ;
    return this;
  }
  private java.sql.Timestamp GONGDIANFASCJSSJ;
  public java.sql.Timestamp get_GONGDIANFASCJSSJ() {
    return GONGDIANFASCJSSJ;
  }
  public void set_GONGDIANFASCJSSJ(java.sql.Timestamp GONGDIANFASCJSSJ) {
    this.GONGDIANFASCJSSJ = GONGDIANFASCJSSJ;
  }
  public YKBZ_DETAIL with_GONGDIANFASCJSSJ(java.sql.Timestamp GONGDIANFASCJSSJ) {
    this.GONGDIANFASCJSSJ = GONGDIANFASCJSSJ;
    return this;
  }
  private java.math.BigDecimal GONGDIANFASCSC;
  public java.math.BigDecimal get_GONGDIANFASCSC() {
    return GONGDIANFASCSC;
  }
  public void set_GONGDIANFASCSC(java.math.BigDecimal GONGDIANFASCSC) {
    this.GONGDIANFASCSC = GONGDIANFASCSC;
  }
  public YKBZ_DETAIL with_GONGDIANFASCSC(java.math.BigDecimal GONGDIANFASCSC) {
    this.GONGDIANFASCSC = GONGDIANFASCSC;
    return this;
  }
  private java.sql.Timestamp GONGDIANFADFKHSJ;
  public java.sql.Timestamp get_GONGDIANFADFKHSJ() {
    return GONGDIANFADFKHSJ;
  }
  public void set_GONGDIANFADFKHSJ(java.sql.Timestamp GONGDIANFADFKHSJ) {
    this.GONGDIANFADFKHSJ = GONGDIANFADFKHSJ;
  }
  public YKBZ_DETAIL with_GONGDIANFADFKHSJ(java.sql.Timestamp GONGDIANFADFKHSJ) {
    this.GONGDIANFADFKHSJ = GONGDIANFADFKHSJ;
    return this;
  }
  private java.math.BigDecimal GONGDIANFADFKHSC;
  public java.math.BigDecimal get_GONGDIANFADFKHSC() {
    return GONGDIANFADFKHSC;
  }
  public void set_GONGDIANFADFKHSC(java.math.BigDecimal GONGDIANFADFKHSC) {
    this.GONGDIANFADFKHSC = GONGDIANFADFKHSC;
  }
  public YKBZ_DETAIL with_GONGDIANFADFKHSC(java.math.BigDecimal GONGDIANFADFKHSC) {
    this.GONGDIANFADFKHSC = GONGDIANFADFKHSC;
    return this;
  }
  private String GONGDIANFADFSJSFCB;
  public String get_GONGDIANFADFSJSFCB() {
    return GONGDIANFADFSJSFCB;
  }
  public void set_GONGDIANFADFSJSFCB(String GONGDIANFADFSJSFCB) {
    this.GONGDIANFADFSJSFCB = GONGDIANFADFSJSFCB;
  }
  public YKBZ_DETAIL with_GONGDIANFADFSJSFCB(String GONGDIANFADFSJSFCB) {
    this.GONGDIANFADFSJSFCB = GONGDIANFADFSJSFCB;
    return this;
  }
  private java.sql.Timestamp GONGDIANFAYHQRSJ;
  public java.sql.Timestamp get_GONGDIANFAYHQRSJ() {
    return GONGDIANFAYHQRSJ;
  }
  public void set_GONGDIANFAYHQRSJ(java.sql.Timestamp GONGDIANFAYHQRSJ) {
    this.GONGDIANFAYHQRSJ = GONGDIANFAYHQRSJ;
  }
  public YKBZ_DETAIL with_GONGDIANFAYHQRSJ(java.sql.Timestamp GONGDIANFAYHQRSJ) {
    this.GONGDIANFAYHQRSJ = GONGDIANFAYHQRSJ;
    return this;
  }
  private java.sql.Timestamp SHOUDIANGCSJZLTJSJ;
  public java.sql.Timestamp get_SHOUDIANGCSJZLTJSJ() {
    return SHOUDIANGCSJZLTJSJ;
  }
  public void set_SHOUDIANGCSJZLTJSJ(java.sql.Timestamp SHOUDIANGCSJZLTJSJ) {
    this.SHOUDIANGCSJZLTJSJ = SHOUDIANGCSJZLTJSJ;
  }
  public YKBZ_DETAIL with_SHOUDIANGCSJZLTJSJ(java.sql.Timestamp SHOUDIANGCSJZLTJSJ) {
    this.SHOUDIANGCSJZLTJSJ = SHOUDIANGCSJZLTJSJ;
    return this;
  }
  private java.sql.Timestamp SHOUDIANGCSJZLSCJSSJ;
  public java.sql.Timestamp get_SHOUDIANGCSJZLSCJSSJ() {
    return SHOUDIANGCSJZLSCJSSJ;
  }
  public void set_SHOUDIANGCSJZLSCJSSJ(java.sql.Timestamp SHOUDIANGCSJZLSCJSSJ) {
    this.SHOUDIANGCSJZLSCJSSJ = SHOUDIANGCSJZLSCJSSJ;
  }
  public YKBZ_DETAIL with_SHOUDIANGCSJZLSCJSSJ(java.sql.Timestamp SHOUDIANGCSJZLSCJSSJ) {
    this.SHOUDIANGCSJZLSCJSSJ = SHOUDIANGCSJZLSCJSSJ;
    return this;
  }
  private java.math.BigDecimal SHOUDIANGCSJZLSHSC;
  public java.math.BigDecimal get_SHOUDIANGCSJZLSHSC() {
    return SHOUDIANGCSJZLSHSC;
  }
  public void set_SHOUDIANGCSJZLSHSC(java.math.BigDecimal SHOUDIANGCSJZLSHSC) {
    this.SHOUDIANGCSJZLSHSC = SHOUDIANGCSJZLSHSC;
  }
  public YKBZ_DETAIL with_SHOUDIANGCSJZLSHSC(java.math.BigDecimal SHOUDIANGCSJZLSHSC) {
    this.SHOUDIANGCSJZLSHSC = SHOUDIANGCSJZLSHSC;
    return this;
  }
  private String SHEJIZLSHSJSFCB;
  public String get_SHEJIZLSHSJSFCB() {
    return SHEJIZLSHSJSFCB;
  }
  public void set_SHEJIZLSHSJSFCB(String SHEJIZLSHSJSFCB) {
    this.SHEJIZLSHSJSFCB = SHEJIZLSHSJSFCB;
  }
  public YKBZ_DETAIL with_SHEJIZLSHSJSFCB(String SHEJIZLSHSJSFCB) {
    this.SHEJIZLSHSJSFCB = SHEJIZLSHSJSFCB;
    return this;
  }
  private java.sql.Timestamp SHOUDIANGCZJJCKSSJ;
  public java.sql.Timestamp get_SHOUDIANGCZJJCKSSJ() {
    return SHOUDIANGCZJJCKSSJ;
  }
  public void set_SHOUDIANGCZJJCKSSJ(java.sql.Timestamp SHOUDIANGCZJJCKSSJ) {
    this.SHOUDIANGCZJJCKSSJ = SHOUDIANGCZJJCKSSJ;
  }
  public YKBZ_DETAIL with_SHOUDIANGCZJJCKSSJ(java.sql.Timestamp SHOUDIANGCZJJCKSSJ) {
    this.SHOUDIANGCZJJCKSSJ = SHOUDIANGCZJJCKSSJ;
    return this;
  }
  private java.sql.Timestamp SHOUDIANGCZJJCJSSJ;
  public java.sql.Timestamp get_SHOUDIANGCZJJCJSSJ() {
    return SHOUDIANGCZJJCJSSJ;
  }
  public void set_SHOUDIANGCZJJCJSSJ(java.sql.Timestamp SHOUDIANGCZJJCJSSJ) {
    this.SHOUDIANGCZJJCJSSJ = SHOUDIANGCZJJCJSSJ;
  }
  public YKBZ_DETAIL with_SHOUDIANGCZJJCJSSJ(java.sql.Timestamp SHOUDIANGCZJJCJSSJ) {
    this.SHOUDIANGCZJJCJSSJ = SHOUDIANGCZJJCJSSJ;
    return this;
  }
  private java.math.BigDecimal SHOUDIANGCZJJCSC;
  public java.math.BigDecimal get_SHOUDIANGCZJJCSC() {
    return SHOUDIANGCZJJCSC;
  }
  public void set_SHOUDIANGCZJJCSC(java.math.BigDecimal SHOUDIANGCZJJCSC) {
    this.SHOUDIANGCZJJCSC = SHOUDIANGCZJJCSC;
  }
  public YKBZ_DETAIL with_SHOUDIANGCZJJCSC(java.math.BigDecimal SHOUDIANGCZJJCSC) {
    this.SHOUDIANGCZJJCSC = SHOUDIANGCZJJCSC;
    return this;
  }
  private String ZHONGJIANJCSJSFCB;
  public String get_ZHONGJIANJCSJSFCB() {
    return ZHONGJIANJCSJSFCB;
  }
  public void set_ZHONGJIANJCSJSFCB(String ZHONGJIANJCSJSFCB) {
    this.ZHONGJIANJCSJSFCB = ZHONGJIANJCSJSFCB;
  }
  public YKBZ_DETAIL with_ZHONGJIANJCSJSFCB(String ZHONGJIANJCSJSFCB) {
    this.ZHONGJIANJCSJSFCB = ZHONGJIANJCSJSFCB;
    return this;
  }
  private java.sql.Timestamp SHOUDIANGCJGYSKSSJ;
  public java.sql.Timestamp get_SHOUDIANGCJGYSKSSJ() {
    return SHOUDIANGCJGYSKSSJ;
  }
  public void set_SHOUDIANGCJGYSKSSJ(java.sql.Timestamp SHOUDIANGCJGYSKSSJ) {
    this.SHOUDIANGCJGYSKSSJ = SHOUDIANGCJGYSKSSJ;
  }
  public YKBZ_DETAIL with_SHOUDIANGCJGYSKSSJ(java.sql.Timestamp SHOUDIANGCJGYSKSSJ) {
    this.SHOUDIANGCJGYSKSSJ = SHOUDIANGCJGYSKSSJ;
    return this;
  }
  private java.sql.Timestamp SHOUDIANGCJGYSJSSJ;
  public java.sql.Timestamp get_SHOUDIANGCJGYSJSSJ() {
    return SHOUDIANGCJGYSJSSJ;
  }
  public void set_SHOUDIANGCJGYSJSSJ(java.sql.Timestamp SHOUDIANGCJGYSJSSJ) {
    this.SHOUDIANGCJGYSJSSJ = SHOUDIANGCJGYSJSSJ;
  }
  public YKBZ_DETAIL with_SHOUDIANGCJGYSJSSJ(java.sql.Timestamp SHOUDIANGCJGYSJSSJ) {
    this.SHOUDIANGCJGYSJSSJ = SHOUDIANGCJGYSJSSJ;
    return this;
  }
  private java.math.BigDecimal SHOUDIANGCJGYSSC;
  public java.math.BigDecimal get_SHOUDIANGCJGYSSC() {
    return SHOUDIANGCJGYSSC;
  }
  public void set_SHOUDIANGCJGYSSC(java.math.BigDecimal SHOUDIANGCJGYSSC) {
    this.SHOUDIANGCJGYSSC = SHOUDIANGCJGYSSC;
  }
  public YKBZ_DETAIL with_SHOUDIANGCJGYSSC(java.math.BigDecimal SHOUDIANGCJGYSSC) {
    this.SHOUDIANGCJGYSSC = SHOUDIANGCJGYSSC;
    return this;
  }
  private String JUNGONGYSSJSFCB;
  public String get_JUNGONGYSSJSFCB() {
    return JUNGONGYSSJSFCB;
  }
  public void set_JUNGONGYSSJSFCB(String JUNGONGYSSJSFCB) {
    this.JUNGONGYSSJSFCB = JUNGONGYSSJSFCB;
  }
  public YKBZ_DETAIL with_JUNGONGYSSJSFCB(String JUNGONGYSSJSFCB) {
    this.JUNGONGYSSJSFCB = JUNGONGYSSJSFCB;
    return this;
  }
  private java.sql.Timestamp GAOYAGYDHTQDSJ;
  public java.sql.Timestamp get_GAOYAGYDHTQDSJ() {
    return GAOYAGYDHTQDSJ;
  }
  public void set_GAOYAGYDHTQDSJ(java.sql.Timestamp GAOYAGYDHTQDSJ) {
    this.GAOYAGYDHTQDSJ = GAOYAGYDHTQDSJ;
  }
  public YKBZ_DETAIL with_GAOYAGYDHTQDSJ(java.sql.Timestamp GAOYAGYDHTQDSJ) {
    this.GAOYAGYDHTQDSJ = GAOYAGYDHTQDSJ;
    return this;
  }
  private java.sql.Timestamp ZHUANGBIAOKSSJ;
  public java.sql.Timestamp get_ZHUANGBIAOKSSJ() {
    return ZHUANGBIAOKSSJ;
  }
  public void set_ZHUANGBIAOKSSJ(java.sql.Timestamp ZHUANGBIAOKSSJ) {
    this.ZHUANGBIAOKSSJ = ZHUANGBIAOKSSJ;
  }
  public YKBZ_DETAIL with_ZHUANGBIAOKSSJ(java.sql.Timestamp ZHUANGBIAOKSSJ) {
    this.ZHUANGBIAOKSSJ = ZHUANGBIAOKSSJ;
    return this;
  }
  private java.sql.Timestamp ZHUANGBIAOWCSJ;
  public java.sql.Timestamp get_ZHUANGBIAOWCSJ() {
    return ZHUANGBIAOWCSJ;
  }
  public void set_ZHUANGBIAOWCSJ(java.sql.Timestamp ZHUANGBIAOWCSJ) {
    this.ZHUANGBIAOWCSJ = ZHUANGBIAOWCSJ;
  }
  public YKBZ_DETAIL with_ZHUANGBIAOWCSJ(java.sql.Timestamp ZHUANGBIAOWCSJ) {
    this.ZHUANGBIAOWCSJ = ZHUANGBIAOWCSJ;
    return this;
  }
  private java.sql.Timestamp QIANRUSDWCSJ;
  public java.sql.Timestamp get_QIANRUSDWCSJ() {
    return QIANRUSDWCSJ;
  }
  public void set_QIANRUSDWCSJ(java.sql.Timestamp QIANRUSDWCSJ) {
    this.QIANRUSDWCSJ = QIANRUSDWCSJ;
  }
  public YKBZ_DETAIL with_QIANRUSDWCSJ(java.sql.Timestamp QIANRUSDWCSJ) {
    this.QIANRUSDWCSJ = QIANRUSDWCSJ;
    return this;
  }
  private java.math.BigDecimal ZHUANGBIAOJDSC;
  public java.math.BigDecimal get_ZHUANGBIAOJDSC() {
    return ZHUANGBIAOJDSC;
  }
  public void set_ZHUANGBIAOJDSC(java.math.BigDecimal ZHUANGBIAOJDSC) {
    this.ZHUANGBIAOJDSC = ZHUANGBIAOJDSC;
  }
  public YKBZ_DETAIL with_ZHUANGBIAOJDSC(java.math.BigDecimal ZHUANGBIAOJDSC) {
    this.ZHUANGBIAOJDSC = ZHUANGBIAOJDSC;
    return this;
  }
  private String ZHUANGBIAOJDSJSFCB;
  public String get_ZHUANGBIAOJDSJSFCB() {
    return ZHUANGBIAOJDSJSFCB;
  }
  public void set_ZHUANGBIAOJDSJSFCB(String ZHUANGBIAOJDSJSFCB) {
    this.ZHUANGBIAOJDSJSFCB = ZHUANGBIAOJDSJSFCB;
  }
  public YKBZ_DETAIL with_ZHUANGBIAOJDSJSFCB(String ZHUANGBIAOJDSJSFCB) {
    this.ZHUANGBIAOJDSJSFCB = ZHUANGBIAOJDSJSFCB;
    return this;
  }
  private java.math.BigDecimal YEKUOFWDDDSC;
  public java.math.BigDecimal get_YEKUOFWDDDSC() {
    return YEKUOFWDDDSC;
  }
  public void set_YEKUOFWDDDSC(java.math.BigDecimal YEKUOFWDDDSC) {
    this.YEKUOFWDDDSC = YEKUOFWDDDSC;
  }
  public YKBZ_DETAIL with_YEKUOFWDDDSC(java.math.BigDecimal YEKUOFWDDDSC) {
    this.YEKUOFWDDDSC = YEKUOFWDDDSC;
    return this;
  }
  private java.sql.Timestamp YEKUOGDWCSJ;
  public java.sql.Timestamp get_YEKUOGDWCSJ() {
    return YEKUOGDWCSJ;
  }
  public void set_YEKUOGDWCSJ(java.sql.Timestamp YEKUOGDWCSJ) {
    this.YEKUOGDWCSJ = YEKUOGDWCSJ;
  }
  public YKBZ_DETAIL with_YEKUOGDWCSJ(java.sql.Timestamp YEKUOGDWCSJ) {
    this.YEKUOGDWCSJ = YEKUOGDWCSJ;
    return this;
  }
  private java.sql.Timestamp GONGDIANFADFKHHFWCSJ;
  public java.sql.Timestamp get_GONGDIANFADFKHHFWCSJ() {
    return GONGDIANFADFKHHFWCSJ;
  }
  public void set_GONGDIANFADFKHHFWCSJ(java.sql.Timestamp GONGDIANFADFKHHFWCSJ) {
    this.GONGDIANFADFKHHFWCSJ = GONGDIANFADFKHHFWCSJ;
  }
  public YKBZ_DETAIL with_GONGDIANFADFKHHFWCSJ(java.sql.Timestamp GONGDIANFADFKHHFWCSJ) {
    this.GONGDIANFADFKHHFWCSJ = GONGDIANFADFKHHFWCSJ;
    return this;
  }
  private java.sql.Timestamp ZHUANGBIAOJDKHHFWCSJ;
  public java.sql.Timestamp get_ZHUANGBIAOJDKHHFWCSJ() {
    return ZHUANGBIAOJDKHHFWCSJ;
  }
  public void set_ZHUANGBIAOJDKHHFWCSJ(java.sql.Timestamp ZHUANGBIAOJDKHHFWCSJ) {
    this.ZHUANGBIAOJDKHHFWCSJ = ZHUANGBIAOJDKHHFWCSJ;
  }
  public YKBZ_DETAIL with_ZHUANGBIAOJDKHHFWCSJ(java.sql.Timestamp ZHUANGBIAOJDKHHFWCSJ) {
    this.ZHUANGBIAOJDKHHFWCSJ = ZHUANGBIAOJDKHHFWCSJ;
    return this;
  }
  private String GONGDIANFADFHJKHHFMYD;
  public String get_GONGDIANFADFHJKHHFMYD() {
    return GONGDIANFADFHJKHHFMYD;
  }
  public void set_GONGDIANFADFHJKHHFMYD(String GONGDIANFADFHJKHHFMYD) {
    this.GONGDIANFADFHJKHHFMYD = GONGDIANFADFHJKHHFMYD;
  }
  public YKBZ_DETAIL with_GONGDIANFADFHJKHHFMYD(String GONGDIANFADFHJKHHFMYD) {
    this.GONGDIANFADFHJKHHFMYD = GONGDIANFADFHJKHHFMYD;
    return this;
  }
  private String ZHUANGBIAOJDHJHFKHMYD;
  public String get_ZHUANGBIAOJDHJHFKHMYD() {
    return ZHUANGBIAOJDHJHFKHMYD;
  }
  public void set_ZHUANGBIAOJDHJHFKHMYD(String ZHUANGBIAOJDHJHFKHMYD) {
    this.ZHUANGBIAOJDHJHFKHMYD = ZHUANGBIAOJDHJHFKHMYD;
  }
  public YKBZ_DETAIL with_ZHUANGBIAOJDHJHFKHMYD(String ZHUANGBIAOJDHJHFKHMYD) {
    this.ZHUANGBIAOJDHJHFKHMYD = ZHUANGBIAOJDHJHFKHMYD;
    return this;
  }
  private String YEKUOLCLX;
  public String get_YEKUOLCLX() {
    return YEKUOLCLX;
  }
  public void set_YEKUOLCLX(String YEKUOLCLX) {
    this.YEKUOLCLX = YEKUOLCLX;
  }
  public YKBZ_DETAIL with_YEKUOLCLX(String YEKUOLCLX) {
    this.YEKUOLCLX = YEKUOLCLX;
    return this;
  }
  private String EXT_VALUE1;
  public String get_EXT_VALUE1() {
    return EXT_VALUE1;
  }
  public void set_EXT_VALUE1(String EXT_VALUE1) {
    this.EXT_VALUE1 = EXT_VALUE1;
  }
  public YKBZ_DETAIL with_EXT_VALUE1(String EXT_VALUE1) {
    this.EXT_VALUE1 = EXT_VALUE1;
    return this;
  }
  private String EXT_VALUE2;
  public String get_EXT_VALUE2() {
    return EXT_VALUE2;
  }
  public void set_EXT_VALUE2(String EXT_VALUE2) {
    this.EXT_VALUE2 = EXT_VALUE2;
  }
  public YKBZ_DETAIL with_EXT_VALUE2(String EXT_VALUE2) {
    this.EXT_VALUE2 = EXT_VALUE2;
    return this;
  }
  private String EXT_VALUE3;
  public String get_EXT_VALUE3() {
    return EXT_VALUE3;
  }
  public void set_EXT_VALUE3(String EXT_VALUE3) {
    this.EXT_VALUE3 = EXT_VALUE3;
  }
  public YKBZ_DETAIL with_EXT_VALUE3(String EXT_VALUE3) {
    this.EXT_VALUE3 = EXT_VALUE3;
    return this;
  }
  private String EXT_VALUE4;
  public String get_EXT_VALUE4() {
    return EXT_VALUE4;
  }
  public void set_EXT_VALUE4(String EXT_VALUE4) {
    this.EXT_VALUE4 = EXT_VALUE4;
  }
  public YKBZ_DETAIL with_EXT_VALUE4(String EXT_VALUE4) {
    this.EXT_VALUE4 = EXT_VALUE4;
    return this;
  }
  private String EXT_VALUE5;
  public String get_EXT_VALUE5() {
    return EXT_VALUE5;
  }
  public void set_EXT_VALUE5(String EXT_VALUE5) {
    this.EXT_VALUE5 = EXT_VALUE5;
  }
  public YKBZ_DETAIL with_EXT_VALUE5(String EXT_VALUE5) {
    this.EXT_VALUE5 = EXT_VALUE5;
    return this;
  }
  private java.sql.Timestamp TIME_STAMP;
  public java.sql.Timestamp get_TIME_STAMP() {
    return TIME_STAMP;
  }
  public void set_TIME_STAMP(java.sql.Timestamp TIME_STAMP) {
    this.TIME_STAMP = TIME_STAMP;
  }
  public YKBZ_DETAIL with_TIME_STAMP(java.sql.Timestamp TIME_STAMP) {
    this.TIME_STAMP = TIME_STAMP;
    return this;
  }
  private String TUISONGSJ;
  public String get_TUISONGSJ() {
    return TUISONGSJ;
  }
  public void set_TUISONGSJ(String TUISONGSJ) {
    this.TUISONGSJ = TUISONGSJ;
  }
  public YKBZ_DETAIL with_TUISONGSJ(String TUISONGSJ) {
    this.TUISONGSJ = TUISONGSJ;
    return this;
  }
  private java.math.BigDecimal VER_NO;
  public java.math.BigDecimal get_VER_NO() {
    return VER_NO;
  }
  public void set_VER_NO(java.math.BigDecimal VER_NO) {
    this.VER_NO = VER_NO;
  }
  public YKBZ_DETAIL with_VER_NO(java.math.BigDecimal VER_NO) {
    this.VER_NO = VER_NO;
    return this;
  }
  private java.math.BigDecimal ALL_TIME;
  public java.math.BigDecimal get_ALL_TIME() {
    return ALL_TIME;
  }
  public void set_ALL_TIME(java.math.BigDecimal ALL_TIME) {
    this.ALL_TIME = ALL_TIME;
  }
  public YKBZ_DETAIL with_ALL_TIME(java.math.BigDecimal ALL_TIME) {
    this.ALL_TIME = ALL_TIME;
    return this;
  }
  private java.math.BigDecimal PLAN_REPLY_TIME;
  public java.math.BigDecimal get_PLAN_REPLY_TIME() {
    return PLAN_REPLY_TIME;
  }
  public void set_PLAN_REPLY_TIME(java.math.BigDecimal PLAN_REPLY_TIME) {
    this.PLAN_REPLY_TIME = PLAN_REPLY_TIME;
  }
  public YKBZ_DETAIL with_PLAN_REPLY_TIME(java.math.BigDecimal PLAN_REPLY_TIME) {
    this.PLAN_REPLY_TIME = PLAN_REPLY_TIME;
    return this;
  }
  private java.math.BigDecimal SCENE_RESEARCH_TIME;
  public java.math.BigDecimal get_SCENE_RESEARCH_TIME() {
    return SCENE_RESEARCH_TIME;
  }
  public void set_SCENE_RESEARCH_TIME(java.math.BigDecimal SCENE_RESEARCH_TIME) {
    this.SCENE_RESEARCH_TIME = SCENE_RESEARCH_TIME;
  }
  public YKBZ_DETAIL with_SCENE_RESEARCH_TIME(java.math.BigDecimal SCENE_RESEARCH_TIME) {
    this.SCENE_RESEARCH_TIME = SCENE_RESEARCH_TIME;
    return this;
  }
  private java.math.BigDecimal PLAN_CHECK_TIME;
  public java.math.BigDecimal get_PLAN_CHECK_TIME() {
    return PLAN_CHECK_TIME;
  }
  public void set_PLAN_CHECK_TIME(java.math.BigDecimal PLAN_CHECK_TIME) {
    this.PLAN_CHECK_TIME = PLAN_CHECK_TIME;
  }
  public YKBZ_DETAIL with_PLAN_CHECK_TIME(java.math.BigDecimal PLAN_CHECK_TIME) {
    this.PLAN_CHECK_TIME = PLAN_CHECK_TIME;
    return this;
  }
  private java.math.BigDecimal DATA_CHECK_TIME;
  public java.math.BigDecimal get_DATA_CHECK_TIME() {
    return DATA_CHECK_TIME;
  }
  public void set_DATA_CHECK_TIME(java.math.BigDecimal DATA_CHECK_TIME) {
    this.DATA_CHECK_TIME = DATA_CHECK_TIME;
  }
  public YKBZ_DETAIL with_DATA_CHECK_TIME(java.math.BigDecimal DATA_CHECK_TIME) {
    this.DATA_CHECK_TIME = DATA_CHECK_TIME;
    return this;
  }
  private java.math.BigDecimal PROCESS_CHECK_TIME;
  public java.math.BigDecimal get_PROCESS_CHECK_TIME() {
    return PROCESS_CHECK_TIME;
  }
  public void set_PROCESS_CHECK_TIME(java.math.BigDecimal PROCESS_CHECK_TIME) {
    this.PROCESS_CHECK_TIME = PROCESS_CHECK_TIME;
  }
  public YKBZ_DETAIL with_PROCESS_CHECK_TIME(java.math.BigDecimal PROCESS_CHECK_TIME) {
    this.PROCESS_CHECK_TIME = PROCESS_CHECK_TIME;
    return this;
  }
  private java.math.BigDecimal COMPLETED_ACCEPT_TIME;
  public java.math.BigDecimal get_COMPLETED_ACCEPT_TIME() {
    return COMPLETED_ACCEPT_TIME;
  }
  public void set_COMPLETED_ACCEPT_TIME(java.math.BigDecimal COMPLETED_ACCEPT_TIME) {
    this.COMPLETED_ACCEPT_TIME = COMPLETED_ACCEPT_TIME;
  }
  public YKBZ_DETAIL with_COMPLETED_ACCEPT_TIME(java.math.BigDecimal COMPLETED_ACCEPT_TIME) {
    this.COMPLETED_ACCEPT_TIME = COMPLETED_ACCEPT_TIME;
    return this;
  }
  private java.math.BigDecimal SEND_ELE_TIME;
  public java.math.BigDecimal get_SEND_ELE_TIME() {
    return SEND_ELE_TIME;
  }
  public void set_SEND_ELE_TIME(java.math.BigDecimal SEND_ELE_TIME) {
    this.SEND_ELE_TIME = SEND_ELE_TIME;
  }
  public YKBZ_DETAIL with_SEND_ELE_TIME(java.math.BigDecimal SEND_ELE_TIME) {
    this.SEND_ELE_TIME = SEND_ELE_TIME;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof YKBZ_DETAIL)) {
      return false;
    }
    YKBZ_DETAIL that = (YKBZ_DETAIL) o;
    boolean equal = true;
    equal = equal && (this.YEKUOGDH == null ? that.YEKUOGDH == null : this.YEKUOGDH.equals(that.YEKUOGDH));
    equal = equal && (this.GONGDANCJRQ == null ? that.GONGDANCJRQ == null : this.GONGDANCJRQ.equals(that.GONGDANCJRQ));
    equal = equal && (this.GONGDIANDW_DS == null ? that.GONGDIANDW_DS == null : this.GONGDIANDW_DS.equals(that.GONGDIANDW_DS));
    equal = equal && (this.GONGDIANDW_XQ == null ? that.GONGDIANDW_XQ == null : this.GONGDIANDW_XQ.equals(that.GONGDIANDW_XQ));
    equal = equal && (this.YONGHUMC == null ? that.YONGHUMC == null : this.YONGHUMC.equals(that.YONGHUMC));
    equal = equal && (this.YONGHUBZRL == null ? that.YONGHUBZRL == null : this.YONGHUBZRL.equals(that.YONGHUBZRL));
    equal = equal && (this.YONGHUYYRL == null ? that.YONGHUYYRL == null : this.YONGHUYYRL.equals(that.YONGHUYYRL));
    equal = equal && (this.DIANYADJ == null ? that.DIANYADJ == null : this.DIANYADJ.equals(that.DIANYADJ));
    equal = equal && (this.YONGDIANLB == null ? that.YONGDIANLB == null : this.YONGDIANLB.equals(that.YONGDIANLB));
    equal = equal && (this.YONGHUXY == null ? that.YONGHUXY == null : this.YONGHUXY.equals(that.YONGHUXY));
    equal = equal && (this.SHIFOUYJRGC == null ? that.SHIFOUYJRGC == null : this.SHIFOUYJRGC.equals(that.SHIFOUYJRGC));
    equal = equal && (this.DANSHUANGDY == null ? that.DANSHUANGDY == null : this.DANSHUANGDY.equals(that.DANSHUANGDY));
    equal = equal && (this.YEWUSLJSSJ == null ? that.YEWUSLJSSJ == null : this.YEWUSLJSSJ.equals(that.YEWUSLJSSJ));
    equal = equal && (this.YEKUOSLSJ == null ? that.YEKUOSLSJ == null : this.YEKUOSLSJ.equals(that.YEKUOSLSJ));
    equal = equal && (this.XIANCHANGKCKSSJ == null ? that.XIANCHANGKCKSSJ == null : this.XIANCHANGKCKSSJ.equals(that.XIANCHANGKCKSSJ));
    equal = equal && (this.XIANCHANGKCJSSJ == null ? that.XIANCHANGKCJSSJ == null : this.XIANCHANGKCJSSJ.equals(that.XIANCHANGKCJSSJ));
    equal = equal && (this.XIANCHANGKCSC == null ? that.XIANCHANGKCSC == null : this.XIANCHANGKCSC.equals(that.XIANCHANGKCSC));
    equal = equal && (this.GONGDIANFASCKSSJ == null ? that.GONGDIANFASCKSSJ == null : this.GONGDIANFASCKSSJ.equals(that.GONGDIANFASCKSSJ));
    equal = equal && (this.GONGDIANFASCJSSJ == null ? that.GONGDIANFASCJSSJ == null : this.GONGDIANFASCJSSJ.equals(that.GONGDIANFASCJSSJ));
    equal = equal && (this.GONGDIANFASCSC == null ? that.GONGDIANFASCSC == null : this.GONGDIANFASCSC.equals(that.GONGDIANFASCSC));
    equal = equal && (this.GONGDIANFADFKHSJ == null ? that.GONGDIANFADFKHSJ == null : this.GONGDIANFADFKHSJ.equals(that.GONGDIANFADFKHSJ));
    equal = equal && (this.GONGDIANFADFKHSC == null ? that.GONGDIANFADFKHSC == null : this.GONGDIANFADFKHSC.equals(that.GONGDIANFADFKHSC));
    equal = equal && (this.GONGDIANFADFSJSFCB == null ? that.GONGDIANFADFSJSFCB == null : this.GONGDIANFADFSJSFCB.equals(that.GONGDIANFADFSJSFCB));
    equal = equal && (this.GONGDIANFAYHQRSJ == null ? that.GONGDIANFAYHQRSJ == null : this.GONGDIANFAYHQRSJ.equals(that.GONGDIANFAYHQRSJ));
    equal = equal && (this.SHOUDIANGCSJZLTJSJ == null ? that.SHOUDIANGCSJZLTJSJ == null : this.SHOUDIANGCSJZLTJSJ.equals(that.SHOUDIANGCSJZLTJSJ));
    equal = equal && (this.SHOUDIANGCSJZLSCJSSJ == null ? that.SHOUDIANGCSJZLSCJSSJ == null : this.SHOUDIANGCSJZLSCJSSJ.equals(that.SHOUDIANGCSJZLSCJSSJ));
    equal = equal && (this.SHOUDIANGCSJZLSHSC == null ? that.SHOUDIANGCSJZLSHSC == null : this.SHOUDIANGCSJZLSHSC.equals(that.SHOUDIANGCSJZLSHSC));
    equal = equal && (this.SHEJIZLSHSJSFCB == null ? that.SHEJIZLSHSJSFCB == null : this.SHEJIZLSHSJSFCB.equals(that.SHEJIZLSHSJSFCB));
    equal = equal && (this.SHOUDIANGCZJJCKSSJ == null ? that.SHOUDIANGCZJJCKSSJ == null : this.SHOUDIANGCZJJCKSSJ.equals(that.SHOUDIANGCZJJCKSSJ));
    equal = equal && (this.SHOUDIANGCZJJCJSSJ == null ? that.SHOUDIANGCZJJCJSSJ == null : this.SHOUDIANGCZJJCJSSJ.equals(that.SHOUDIANGCZJJCJSSJ));
    equal = equal && (this.SHOUDIANGCZJJCSC == null ? that.SHOUDIANGCZJJCSC == null : this.SHOUDIANGCZJJCSC.equals(that.SHOUDIANGCZJJCSC));
    equal = equal && (this.ZHONGJIANJCSJSFCB == null ? that.ZHONGJIANJCSJSFCB == null : this.ZHONGJIANJCSJSFCB.equals(that.ZHONGJIANJCSJSFCB));
    equal = equal && (this.SHOUDIANGCJGYSKSSJ == null ? that.SHOUDIANGCJGYSKSSJ == null : this.SHOUDIANGCJGYSKSSJ.equals(that.SHOUDIANGCJGYSKSSJ));
    equal = equal && (this.SHOUDIANGCJGYSJSSJ == null ? that.SHOUDIANGCJGYSJSSJ == null : this.SHOUDIANGCJGYSJSSJ.equals(that.SHOUDIANGCJGYSJSSJ));
    equal = equal && (this.SHOUDIANGCJGYSSC == null ? that.SHOUDIANGCJGYSSC == null : this.SHOUDIANGCJGYSSC.equals(that.SHOUDIANGCJGYSSC));
    equal = equal && (this.JUNGONGYSSJSFCB == null ? that.JUNGONGYSSJSFCB == null : this.JUNGONGYSSJSFCB.equals(that.JUNGONGYSSJSFCB));
    equal = equal && (this.GAOYAGYDHTQDSJ == null ? that.GAOYAGYDHTQDSJ == null : this.GAOYAGYDHTQDSJ.equals(that.GAOYAGYDHTQDSJ));
    equal = equal && (this.ZHUANGBIAOKSSJ == null ? that.ZHUANGBIAOKSSJ == null : this.ZHUANGBIAOKSSJ.equals(that.ZHUANGBIAOKSSJ));
    equal = equal && (this.ZHUANGBIAOWCSJ == null ? that.ZHUANGBIAOWCSJ == null : this.ZHUANGBIAOWCSJ.equals(that.ZHUANGBIAOWCSJ));
    equal = equal && (this.QIANRUSDWCSJ == null ? that.QIANRUSDWCSJ == null : this.QIANRUSDWCSJ.equals(that.QIANRUSDWCSJ));
    equal = equal && (this.ZHUANGBIAOJDSC == null ? that.ZHUANGBIAOJDSC == null : this.ZHUANGBIAOJDSC.equals(that.ZHUANGBIAOJDSC));
    equal = equal && (this.ZHUANGBIAOJDSJSFCB == null ? that.ZHUANGBIAOJDSJSFCB == null : this.ZHUANGBIAOJDSJSFCB.equals(that.ZHUANGBIAOJDSJSFCB));
    equal = equal && (this.YEKUOFWDDDSC == null ? that.YEKUOFWDDDSC == null : this.YEKUOFWDDDSC.equals(that.YEKUOFWDDDSC));
    equal = equal && (this.YEKUOGDWCSJ == null ? that.YEKUOGDWCSJ == null : this.YEKUOGDWCSJ.equals(that.YEKUOGDWCSJ));
    equal = equal && (this.GONGDIANFADFKHHFWCSJ == null ? that.GONGDIANFADFKHHFWCSJ == null : this.GONGDIANFADFKHHFWCSJ.equals(that.GONGDIANFADFKHHFWCSJ));
    equal = equal && (this.ZHUANGBIAOJDKHHFWCSJ == null ? that.ZHUANGBIAOJDKHHFWCSJ == null : this.ZHUANGBIAOJDKHHFWCSJ.equals(that.ZHUANGBIAOJDKHHFWCSJ));
    equal = equal && (this.GONGDIANFADFHJKHHFMYD == null ? that.GONGDIANFADFHJKHHFMYD == null : this.GONGDIANFADFHJKHHFMYD.equals(that.GONGDIANFADFHJKHHFMYD));
    equal = equal && (this.ZHUANGBIAOJDHJHFKHMYD == null ? that.ZHUANGBIAOJDHJHFKHMYD == null : this.ZHUANGBIAOJDHJHFKHMYD.equals(that.ZHUANGBIAOJDHJHFKHMYD));
    equal = equal && (this.YEKUOLCLX == null ? that.YEKUOLCLX == null : this.YEKUOLCLX.equals(that.YEKUOLCLX));
    equal = equal && (this.EXT_VALUE1 == null ? that.EXT_VALUE1 == null : this.EXT_VALUE1.equals(that.EXT_VALUE1));
    equal = equal && (this.EXT_VALUE2 == null ? that.EXT_VALUE2 == null : this.EXT_VALUE2.equals(that.EXT_VALUE2));
    equal = equal && (this.EXT_VALUE3 == null ? that.EXT_VALUE3 == null : this.EXT_VALUE3.equals(that.EXT_VALUE3));
    equal = equal && (this.EXT_VALUE4 == null ? that.EXT_VALUE4 == null : this.EXT_VALUE4.equals(that.EXT_VALUE4));
    equal = equal && (this.EXT_VALUE5 == null ? that.EXT_VALUE5 == null : this.EXT_VALUE5.equals(that.EXT_VALUE5));
    equal = equal && (this.TIME_STAMP == null ? that.TIME_STAMP == null : this.TIME_STAMP.equals(that.TIME_STAMP));
    equal = equal && (this.TUISONGSJ == null ? that.TUISONGSJ == null : this.TUISONGSJ.equals(that.TUISONGSJ));
    equal = equal && (this.VER_NO == null ? that.VER_NO == null : this.VER_NO.equals(that.VER_NO));
    equal = equal && (this.ALL_TIME == null ? that.ALL_TIME == null : this.ALL_TIME.equals(that.ALL_TIME));
    equal = equal && (this.PLAN_REPLY_TIME == null ? that.PLAN_REPLY_TIME == null : this.PLAN_REPLY_TIME.equals(that.PLAN_REPLY_TIME));
    equal = equal && (this.SCENE_RESEARCH_TIME == null ? that.SCENE_RESEARCH_TIME == null : this.SCENE_RESEARCH_TIME.equals(that.SCENE_RESEARCH_TIME));
    equal = equal && (this.PLAN_CHECK_TIME == null ? that.PLAN_CHECK_TIME == null : this.PLAN_CHECK_TIME.equals(that.PLAN_CHECK_TIME));
    equal = equal && (this.DATA_CHECK_TIME == null ? that.DATA_CHECK_TIME == null : this.DATA_CHECK_TIME.equals(that.DATA_CHECK_TIME));
    equal = equal && (this.PROCESS_CHECK_TIME == null ? that.PROCESS_CHECK_TIME == null : this.PROCESS_CHECK_TIME.equals(that.PROCESS_CHECK_TIME));
    equal = equal && (this.COMPLETED_ACCEPT_TIME == null ? that.COMPLETED_ACCEPT_TIME == null : this.COMPLETED_ACCEPT_TIME.equals(that.COMPLETED_ACCEPT_TIME));
    equal = equal && (this.SEND_ELE_TIME == null ? that.SEND_ELE_TIME == null : this.SEND_ELE_TIME.equals(that.SEND_ELE_TIME));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.YEKUOGDH = JdbcWritableBridge.readString(1, __dbResults);
    this.GONGDANCJRQ = JdbcWritableBridge.readTimestamp(2, __dbResults);
    this.GONGDIANDW_DS = JdbcWritableBridge.readString(3, __dbResults);
    this.GONGDIANDW_XQ = JdbcWritableBridge.readString(4, __dbResults);
    this.YONGHUMC = JdbcWritableBridge.readString(5, __dbResults);
    this.YONGHUBZRL = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.YONGHUYYRL = JdbcWritableBridge.readBigDecimal(7, __dbResults);
    this.DIANYADJ = JdbcWritableBridge.readString(8, __dbResults);
    this.YONGDIANLB = JdbcWritableBridge.readString(9, __dbResults);
    this.YONGHUXY = JdbcWritableBridge.readString(10, __dbResults);
    this.SHIFOUYJRGC = JdbcWritableBridge.readString(11, __dbResults);
    this.DANSHUANGDY = JdbcWritableBridge.readString(12, __dbResults);
    this.YEWUSLJSSJ = JdbcWritableBridge.readTimestamp(13, __dbResults);
    this.YEKUOSLSJ = JdbcWritableBridge.readTimestamp(14, __dbResults);
    this.XIANCHANGKCKSSJ = JdbcWritableBridge.readTimestamp(15, __dbResults);
    this.XIANCHANGKCJSSJ = JdbcWritableBridge.readTimestamp(16, __dbResults);
    this.XIANCHANGKCSC = JdbcWritableBridge.readBigDecimal(17, __dbResults);
    this.GONGDIANFASCKSSJ = JdbcWritableBridge.readTimestamp(18, __dbResults);
    this.GONGDIANFASCJSSJ = JdbcWritableBridge.readTimestamp(19, __dbResults);
    this.GONGDIANFASCSC = JdbcWritableBridge.readBigDecimal(20, __dbResults);
    this.GONGDIANFADFKHSJ = JdbcWritableBridge.readTimestamp(21, __dbResults);
    this.GONGDIANFADFKHSC = JdbcWritableBridge.readBigDecimal(22, __dbResults);
    this.GONGDIANFADFSJSFCB = JdbcWritableBridge.readString(23, __dbResults);
    this.GONGDIANFAYHQRSJ = JdbcWritableBridge.readTimestamp(24, __dbResults);
    this.SHOUDIANGCSJZLTJSJ = JdbcWritableBridge.readTimestamp(25, __dbResults);
    this.SHOUDIANGCSJZLSCJSSJ = JdbcWritableBridge.readTimestamp(26, __dbResults);
    this.SHOUDIANGCSJZLSHSC = JdbcWritableBridge.readBigDecimal(27, __dbResults);
    this.SHEJIZLSHSJSFCB = JdbcWritableBridge.readString(28, __dbResults);
    this.SHOUDIANGCZJJCKSSJ = JdbcWritableBridge.readTimestamp(29, __dbResults);
    this.SHOUDIANGCZJJCJSSJ = JdbcWritableBridge.readTimestamp(30, __dbResults);
    this.SHOUDIANGCZJJCSC = JdbcWritableBridge.readBigDecimal(31, __dbResults);
    this.ZHONGJIANJCSJSFCB = JdbcWritableBridge.readString(32, __dbResults);
    this.SHOUDIANGCJGYSKSSJ = JdbcWritableBridge.readTimestamp(33, __dbResults);
    this.SHOUDIANGCJGYSJSSJ = JdbcWritableBridge.readTimestamp(34, __dbResults);
    this.SHOUDIANGCJGYSSC = JdbcWritableBridge.readBigDecimal(35, __dbResults);
    this.JUNGONGYSSJSFCB = JdbcWritableBridge.readString(36, __dbResults);
    this.GAOYAGYDHTQDSJ = JdbcWritableBridge.readTimestamp(37, __dbResults);
    this.ZHUANGBIAOKSSJ = JdbcWritableBridge.readTimestamp(38, __dbResults);
    this.ZHUANGBIAOWCSJ = JdbcWritableBridge.readTimestamp(39, __dbResults);
    this.QIANRUSDWCSJ = JdbcWritableBridge.readTimestamp(40, __dbResults);
    this.ZHUANGBIAOJDSC = JdbcWritableBridge.readBigDecimal(41, __dbResults);
    this.ZHUANGBIAOJDSJSFCB = JdbcWritableBridge.readString(42, __dbResults);
    this.YEKUOFWDDDSC = JdbcWritableBridge.readBigDecimal(43, __dbResults);
    this.YEKUOGDWCSJ = JdbcWritableBridge.readTimestamp(44, __dbResults);
    this.GONGDIANFADFKHHFWCSJ = JdbcWritableBridge.readTimestamp(45, __dbResults);
    this.ZHUANGBIAOJDKHHFWCSJ = JdbcWritableBridge.readTimestamp(46, __dbResults);
    this.GONGDIANFADFHJKHHFMYD = JdbcWritableBridge.readString(47, __dbResults);
    this.ZHUANGBIAOJDHJHFKHMYD = JdbcWritableBridge.readString(48, __dbResults);
    this.YEKUOLCLX = JdbcWritableBridge.readString(49, __dbResults);
    this.EXT_VALUE1 = JdbcWritableBridge.readString(50, __dbResults);
    this.EXT_VALUE2 = JdbcWritableBridge.readString(51, __dbResults);
    this.EXT_VALUE3 = JdbcWritableBridge.readString(52, __dbResults);
    this.EXT_VALUE4 = JdbcWritableBridge.readString(53, __dbResults);
    this.EXT_VALUE5 = JdbcWritableBridge.readString(54, __dbResults);
    this.TIME_STAMP = JdbcWritableBridge.readTimestamp(55, __dbResults);
    this.TUISONGSJ = JdbcWritableBridge.readString(56, __dbResults);
    this.VER_NO = JdbcWritableBridge.readBigDecimal(57, __dbResults);
    this.ALL_TIME = JdbcWritableBridge.readBigDecimal(58, __dbResults);
    this.PLAN_REPLY_TIME = JdbcWritableBridge.readBigDecimal(59, __dbResults);
    this.SCENE_RESEARCH_TIME = JdbcWritableBridge.readBigDecimal(60, __dbResults);
    this.PLAN_CHECK_TIME = JdbcWritableBridge.readBigDecimal(61, __dbResults);
    this.DATA_CHECK_TIME = JdbcWritableBridge.readBigDecimal(62, __dbResults);
    this.PROCESS_CHECK_TIME = JdbcWritableBridge.readBigDecimal(63, __dbResults);
    this.COMPLETED_ACCEPT_TIME = JdbcWritableBridge.readBigDecimal(64, __dbResults);
    this.SEND_ELE_TIME = JdbcWritableBridge.readBigDecimal(65, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(YEKUOGDH, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(GONGDANCJRQ, 2 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(GONGDIANDW_DS, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(GONGDIANDW_XQ, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(YONGHUMC, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(YONGHUBZRL, 6 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(YONGHUYYRL, 7 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(DIANYADJ, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(YONGDIANLB, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(YONGHUXY, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(SHIFOUYJRGC, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(DANSHUANGDY, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(YEWUSLJSSJ, 13 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(YEKUOSLSJ, 14 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(XIANCHANGKCKSSJ, 15 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(XIANCHANGKCJSSJ, 16 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(XIANCHANGKCSC, 17 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeTimestamp(GONGDIANFASCKSSJ, 18 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(GONGDIANFASCJSSJ, 19 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(GONGDIANFASCSC, 20 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeTimestamp(GONGDIANFADFKHSJ, 21 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(GONGDIANFADFKHSC, 22 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(GONGDIANFADFSJSFCB, 23 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(GONGDIANFAYHQRSJ, 24 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(SHOUDIANGCSJZLTJSJ, 25 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(SHOUDIANGCSJZLSCJSSJ, 26 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(SHOUDIANGCSJZLSHSC, 27 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(SHEJIZLSHSJSFCB, 28 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(SHOUDIANGCZJJCKSSJ, 29 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(SHOUDIANGCZJJCJSSJ, 30 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(SHOUDIANGCZJJCSC, 31 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(ZHONGJIANJCSJSFCB, 32 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(SHOUDIANGCJGYSKSSJ, 33 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(SHOUDIANGCJGYSJSSJ, 34 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(SHOUDIANGCJGYSSC, 35 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(JUNGONGYSSJSFCB, 36 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(GAOYAGYDHTQDSJ, 37 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(ZHUANGBIAOKSSJ, 38 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(ZHUANGBIAOWCSJ, 39 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(QIANRUSDWCSJ, 40 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(ZHUANGBIAOJDSC, 41 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(ZHUANGBIAOJDSJSFCB, 42 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(YEKUOFWDDDSC, 43 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeTimestamp(YEKUOGDWCSJ, 44 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(GONGDIANFADFKHHFWCSJ, 45 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(ZHUANGBIAOJDKHHFWCSJ, 46 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(GONGDIANFADFHJKHHFMYD, 47 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ZHUANGBIAOJDHJHFKHMYD, 48 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(YEKUOLCLX, 49 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(EXT_VALUE1, 50 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(EXT_VALUE2, 51 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(EXT_VALUE3, 52 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(EXT_VALUE4, 53 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(EXT_VALUE5, 54 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(TIME_STAMP, 55 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(TUISONGSJ, 56 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(VER_NO, 57 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(ALL_TIME, 58 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(PLAN_REPLY_TIME, 59 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(SCENE_RESEARCH_TIME, 60 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(PLAN_CHECK_TIME, 61 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(DATA_CHECK_TIME, 62 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(PROCESS_CHECK_TIME, 63 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(COMPLETED_ACCEPT_TIME, 64 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(SEND_ELE_TIME, 65 + __off, 2, __dbStmt);
    return 65;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.YEKUOGDH = null;
    } else {
    this.YEKUOGDH = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDANCJRQ = null;
    } else {
    this.GONGDANCJRQ = new Timestamp(__dataIn.readLong());
    this.GONGDANCJRQ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANDW_DS = null;
    } else {
    this.GONGDIANDW_DS = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANDW_XQ = null;
    } else {
    this.GONGDIANDW_XQ = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YONGHUMC = null;
    } else {
    this.YONGHUMC = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YONGHUBZRL = null;
    } else {
    this.YONGHUBZRL = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YONGHUYYRL = null;
    } else {
    this.YONGHUYYRL = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.DIANYADJ = null;
    } else {
    this.DIANYADJ = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YONGDIANLB = null;
    } else {
    this.YONGDIANLB = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YONGHUXY = null;
    } else {
    this.YONGHUXY = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SHIFOUYJRGC = null;
    } else {
    this.SHIFOUYJRGC = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.DANSHUANGDY = null;
    } else {
    this.DANSHUANGDY = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YEWUSLJSSJ = null;
    } else {
    this.YEWUSLJSSJ = new Timestamp(__dataIn.readLong());
    this.YEWUSLJSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.YEKUOSLSJ = null;
    } else {
    this.YEKUOSLSJ = new Timestamp(__dataIn.readLong());
    this.YEKUOSLSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.XIANCHANGKCKSSJ = null;
    } else {
    this.XIANCHANGKCKSSJ = new Timestamp(__dataIn.readLong());
    this.XIANCHANGKCKSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.XIANCHANGKCJSSJ = null;
    } else {
    this.XIANCHANGKCJSSJ = new Timestamp(__dataIn.readLong());
    this.XIANCHANGKCJSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.XIANCHANGKCSC = null;
    } else {
    this.XIANCHANGKCSC = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANFASCKSSJ = null;
    } else {
    this.GONGDIANFASCKSSJ = new Timestamp(__dataIn.readLong());
    this.GONGDIANFASCKSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANFASCJSSJ = null;
    } else {
    this.GONGDIANFASCJSSJ = new Timestamp(__dataIn.readLong());
    this.GONGDIANFASCJSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANFASCSC = null;
    } else {
    this.GONGDIANFASCSC = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANFADFKHSJ = null;
    } else {
    this.GONGDIANFADFKHSJ = new Timestamp(__dataIn.readLong());
    this.GONGDIANFADFKHSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANFADFKHSC = null;
    } else {
    this.GONGDIANFADFKHSC = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANFADFSJSFCB = null;
    } else {
    this.GONGDIANFADFSJSFCB = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANFAYHQRSJ = null;
    } else {
    this.GONGDIANFAYHQRSJ = new Timestamp(__dataIn.readLong());
    this.GONGDIANFAYHQRSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.SHOUDIANGCSJZLTJSJ = null;
    } else {
    this.SHOUDIANGCSJZLTJSJ = new Timestamp(__dataIn.readLong());
    this.SHOUDIANGCSJZLTJSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.SHOUDIANGCSJZLSCJSSJ = null;
    } else {
    this.SHOUDIANGCSJZLSCJSSJ = new Timestamp(__dataIn.readLong());
    this.SHOUDIANGCSJZLSCJSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.SHOUDIANGCSJZLSHSC = null;
    } else {
    this.SHOUDIANGCSJZLSHSC = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SHEJIZLSHSJSFCB = null;
    } else {
    this.SHEJIZLSHSJSFCB = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SHOUDIANGCZJJCKSSJ = null;
    } else {
    this.SHOUDIANGCZJJCKSSJ = new Timestamp(__dataIn.readLong());
    this.SHOUDIANGCZJJCKSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.SHOUDIANGCZJJCJSSJ = null;
    } else {
    this.SHOUDIANGCZJJCJSSJ = new Timestamp(__dataIn.readLong());
    this.SHOUDIANGCZJJCJSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.SHOUDIANGCZJJCSC = null;
    } else {
    this.SHOUDIANGCZJJCSC = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ZHONGJIANJCSJSFCB = null;
    } else {
    this.ZHONGJIANJCSJSFCB = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SHOUDIANGCJGYSKSSJ = null;
    } else {
    this.SHOUDIANGCJGYSKSSJ = new Timestamp(__dataIn.readLong());
    this.SHOUDIANGCJGYSKSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.SHOUDIANGCJGYSJSSJ = null;
    } else {
    this.SHOUDIANGCJGYSJSSJ = new Timestamp(__dataIn.readLong());
    this.SHOUDIANGCJGYSJSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.SHOUDIANGCJGYSSC = null;
    } else {
    this.SHOUDIANGCJGYSSC = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.JUNGONGYSSJSFCB = null;
    } else {
    this.JUNGONGYSSJSFCB = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.GAOYAGYDHTQDSJ = null;
    } else {
    this.GAOYAGYDHTQDSJ = new Timestamp(__dataIn.readLong());
    this.GAOYAGYDHTQDSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.ZHUANGBIAOKSSJ = null;
    } else {
    this.ZHUANGBIAOKSSJ = new Timestamp(__dataIn.readLong());
    this.ZHUANGBIAOKSSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.ZHUANGBIAOWCSJ = null;
    } else {
    this.ZHUANGBIAOWCSJ = new Timestamp(__dataIn.readLong());
    this.ZHUANGBIAOWCSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.QIANRUSDWCSJ = null;
    } else {
    this.QIANRUSDWCSJ = new Timestamp(__dataIn.readLong());
    this.QIANRUSDWCSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.ZHUANGBIAOJDSC = null;
    } else {
    this.ZHUANGBIAOJDSC = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ZHUANGBIAOJDSJSFCB = null;
    } else {
    this.ZHUANGBIAOJDSJSFCB = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YEKUOFWDDDSC = null;
    } else {
    this.YEKUOFWDDDSC = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YEKUOGDWCSJ = null;
    } else {
    this.YEKUOGDWCSJ = new Timestamp(__dataIn.readLong());
    this.YEKUOGDWCSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANFADFKHHFWCSJ = null;
    } else {
    this.GONGDIANFADFKHHFWCSJ = new Timestamp(__dataIn.readLong());
    this.GONGDIANFADFKHHFWCSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.ZHUANGBIAOJDKHHFWCSJ = null;
    } else {
    this.ZHUANGBIAOJDKHHFWCSJ = new Timestamp(__dataIn.readLong());
    this.ZHUANGBIAOJDKHHFWCSJ.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.GONGDIANFADFHJKHHFMYD = null;
    } else {
    this.GONGDIANFADFHJKHHFMYD = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ZHUANGBIAOJDHJHFKHMYD = null;
    } else {
    this.ZHUANGBIAOJDHJHFKHMYD = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.YEKUOLCLX = null;
    } else {
    this.YEKUOLCLX = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.EXT_VALUE1 = null;
    } else {
    this.EXT_VALUE1 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.EXT_VALUE2 = null;
    } else {
    this.EXT_VALUE2 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.EXT_VALUE3 = null;
    } else {
    this.EXT_VALUE3 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.EXT_VALUE4 = null;
    } else {
    this.EXT_VALUE4 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.EXT_VALUE5 = null;
    } else {
    this.EXT_VALUE5 = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.TIME_STAMP = null;
    } else {
    this.TIME_STAMP = new Timestamp(__dataIn.readLong());
    this.TIME_STAMP.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.TUISONGSJ = null;
    } else {
    this.TUISONGSJ = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.VER_NO = null;
    } else {
    this.VER_NO = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ALL_TIME = null;
    } else {
    this.ALL_TIME = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.PLAN_REPLY_TIME = null;
    } else {
    this.PLAN_REPLY_TIME = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SCENE_RESEARCH_TIME = null;
    } else {
    this.SCENE_RESEARCH_TIME = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.PLAN_CHECK_TIME = null;
    } else {
    this.PLAN_CHECK_TIME = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.DATA_CHECK_TIME = null;
    } else {
    this.DATA_CHECK_TIME = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.PROCESS_CHECK_TIME = null;
    } else {
    this.PROCESS_CHECK_TIME = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.COMPLETED_ACCEPT_TIME = null;
    } else {
    this.COMPLETED_ACCEPT_TIME = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.SEND_ELE_TIME = null;
    } else {
    this.SEND_ELE_TIME = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.YEKUOGDH) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, YEKUOGDH);
    }
    if (null == this.GONGDANCJRQ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.GONGDANCJRQ.getTime());
    __dataOut.writeInt(this.GONGDANCJRQ.getNanos());
    }
    if (null == this.GONGDIANDW_DS) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, GONGDIANDW_DS);
    }
    if (null == this.GONGDIANDW_XQ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, GONGDIANDW_XQ);
    }
    if (null == this.YONGHUMC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, YONGHUMC);
    }
    if (null == this.YONGHUBZRL) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.YONGHUBZRL, __dataOut);
    }
    if (null == this.YONGHUYYRL) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.YONGHUYYRL, __dataOut);
    }
    if (null == this.DIANYADJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, DIANYADJ);
    }
    if (null == this.YONGDIANLB) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, YONGDIANLB);
    }
    if (null == this.YONGHUXY) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, YONGHUXY);
    }
    if (null == this.SHIFOUYJRGC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, SHIFOUYJRGC);
    }
    if (null == this.DANSHUANGDY) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, DANSHUANGDY);
    }
    if (null == this.YEWUSLJSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.YEWUSLJSSJ.getTime());
    __dataOut.writeInt(this.YEWUSLJSSJ.getNanos());
    }
    if (null == this.YEKUOSLSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.YEKUOSLSJ.getTime());
    __dataOut.writeInt(this.YEKUOSLSJ.getNanos());
    }
    if (null == this.XIANCHANGKCKSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.XIANCHANGKCKSSJ.getTime());
    __dataOut.writeInt(this.XIANCHANGKCKSSJ.getNanos());
    }
    if (null == this.XIANCHANGKCJSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.XIANCHANGKCJSSJ.getTime());
    __dataOut.writeInt(this.XIANCHANGKCJSSJ.getNanos());
    }
    if (null == this.XIANCHANGKCSC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.XIANCHANGKCSC, __dataOut);
    }
    if (null == this.GONGDIANFASCKSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.GONGDIANFASCKSSJ.getTime());
    __dataOut.writeInt(this.GONGDIANFASCKSSJ.getNanos());
    }
    if (null == this.GONGDIANFASCJSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.GONGDIANFASCJSSJ.getTime());
    __dataOut.writeInt(this.GONGDIANFASCJSSJ.getNanos());
    }
    if (null == this.GONGDIANFASCSC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.GONGDIANFASCSC, __dataOut);
    }
    if (null == this.GONGDIANFADFKHSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.GONGDIANFADFKHSJ.getTime());
    __dataOut.writeInt(this.GONGDIANFADFKHSJ.getNanos());
    }
    if (null == this.GONGDIANFADFKHSC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.GONGDIANFADFKHSC, __dataOut);
    }
    if (null == this.GONGDIANFADFSJSFCB) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, GONGDIANFADFSJSFCB);
    }
    if (null == this.GONGDIANFAYHQRSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.GONGDIANFAYHQRSJ.getTime());
    __dataOut.writeInt(this.GONGDIANFAYHQRSJ.getNanos());
    }
    if (null == this.SHOUDIANGCSJZLTJSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.SHOUDIANGCSJZLTJSJ.getTime());
    __dataOut.writeInt(this.SHOUDIANGCSJZLTJSJ.getNanos());
    }
    if (null == this.SHOUDIANGCSJZLSCJSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.SHOUDIANGCSJZLSCJSSJ.getTime());
    __dataOut.writeInt(this.SHOUDIANGCSJZLSCJSSJ.getNanos());
    }
    if (null == this.SHOUDIANGCSJZLSHSC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.SHOUDIANGCSJZLSHSC, __dataOut);
    }
    if (null == this.SHEJIZLSHSJSFCB) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, SHEJIZLSHSJSFCB);
    }
    if (null == this.SHOUDIANGCZJJCKSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.SHOUDIANGCZJJCKSSJ.getTime());
    __dataOut.writeInt(this.SHOUDIANGCZJJCKSSJ.getNanos());
    }
    if (null == this.SHOUDIANGCZJJCJSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.SHOUDIANGCZJJCJSSJ.getTime());
    __dataOut.writeInt(this.SHOUDIANGCZJJCJSSJ.getNanos());
    }
    if (null == this.SHOUDIANGCZJJCSC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.SHOUDIANGCZJJCSC, __dataOut);
    }
    if (null == this.ZHONGJIANJCSJSFCB) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ZHONGJIANJCSJSFCB);
    }
    if (null == this.SHOUDIANGCJGYSKSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.SHOUDIANGCJGYSKSSJ.getTime());
    __dataOut.writeInt(this.SHOUDIANGCJGYSKSSJ.getNanos());
    }
    if (null == this.SHOUDIANGCJGYSJSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.SHOUDIANGCJGYSJSSJ.getTime());
    __dataOut.writeInt(this.SHOUDIANGCJGYSJSSJ.getNanos());
    }
    if (null == this.SHOUDIANGCJGYSSC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.SHOUDIANGCJGYSSC, __dataOut);
    }
    if (null == this.JUNGONGYSSJSFCB) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, JUNGONGYSSJSFCB);
    }
    if (null == this.GAOYAGYDHTQDSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.GAOYAGYDHTQDSJ.getTime());
    __dataOut.writeInt(this.GAOYAGYDHTQDSJ.getNanos());
    }
    if (null == this.ZHUANGBIAOKSSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.ZHUANGBIAOKSSJ.getTime());
    __dataOut.writeInt(this.ZHUANGBIAOKSSJ.getNanos());
    }
    if (null == this.ZHUANGBIAOWCSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.ZHUANGBIAOWCSJ.getTime());
    __dataOut.writeInt(this.ZHUANGBIAOWCSJ.getNanos());
    }
    if (null == this.QIANRUSDWCSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.QIANRUSDWCSJ.getTime());
    __dataOut.writeInt(this.QIANRUSDWCSJ.getNanos());
    }
    if (null == this.ZHUANGBIAOJDSC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.ZHUANGBIAOJDSC, __dataOut);
    }
    if (null == this.ZHUANGBIAOJDSJSFCB) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ZHUANGBIAOJDSJSFCB);
    }
    if (null == this.YEKUOFWDDDSC) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.YEKUOFWDDDSC, __dataOut);
    }
    if (null == this.YEKUOGDWCSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.YEKUOGDWCSJ.getTime());
    __dataOut.writeInt(this.YEKUOGDWCSJ.getNanos());
    }
    if (null == this.GONGDIANFADFKHHFWCSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.GONGDIANFADFKHHFWCSJ.getTime());
    __dataOut.writeInt(this.GONGDIANFADFKHHFWCSJ.getNanos());
    }
    if (null == this.ZHUANGBIAOJDKHHFWCSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.ZHUANGBIAOJDKHHFWCSJ.getTime());
    __dataOut.writeInt(this.ZHUANGBIAOJDKHHFWCSJ.getNanos());
    }
    if (null == this.GONGDIANFADFHJKHHFMYD) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, GONGDIANFADFHJKHHFMYD);
    }
    if (null == this.ZHUANGBIAOJDHJHFKHMYD) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ZHUANGBIAOJDHJHFKHMYD);
    }
    if (null == this.YEKUOLCLX) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, YEKUOLCLX);
    }
    if (null == this.EXT_VALUE1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, EXT_VALUE1);
    }
    if (null == this.EXT_VALUE2) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, EXT_VALUE2);
    }
    if (null == this.EXT_VALUE3) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, EXT_VALUE3);
    }
    if (null == this.EXT_VALUE4) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, EXT_VALUE4);
    }
    if (null == this.EXT_VALUE5) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, EXT_VALUE5);
    }
    if (null == this.TIME_STAMP) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.TIME_STAMP.getTime());
    __dataOut.writeInt(this.TIME_STAMP.getNanos());
    }
    if (null == this.TUISONGSJ) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, TUISONGSJ);
    }
    if (null == this.VER_NO) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.VER_NO, __dataOut);
    }
    if (null == this.ALL_TIME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.ALL_TIME, __dataOut);
    }
    if (null == this.PLAN_REPLY_TIME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.PLAN_REPLY_TIME, __dataOut);
    }
    if (null == this.SCENE_RESEARCH_TIME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.SCENE_RESEARCH_TIME, __dataOut);
    }
    if (null == this.PLAN_CHECK_TIME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.PLAN_CHECK_TIME, __dataOut);
    }
    if (null == this.DATA_CHECK_TIME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.DATA_CHECK_TIME, __dataOut);
    }
    if (null == this.PROCESS_CHECK_TIME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.PROCESS_CHECK_TIME, __dataOut);
    }
    if (null == this.COMPLETED_ACCEPT_TIME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.COMPLETED_ACCEPT_TIME, __dataOut);
    }
    if (null == this.SEND_ELE_TIME) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.SEND_ELE_TIME, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(YEKUOGDH==null?"null":YEKUOGDH, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDANCJRQ==null?"null":"" + GONGDANCJRQ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANDW_DS==null?"null":GONGDIANDW_DS, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANDW_XQ==null?"null":GONGDIANDW_XQ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YONGHUMC==null?"null":YONGHUMC, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YONGHUBZRL==null?"null":YONGHUBZRL.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YONGHUYYRL==null?"null":YONGHUYYRL.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(DIANYADJ==null?"null":DIANYADJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YONGDIANLB==null?"null":YONGDIANLB, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YONGHUXY==null?"null":YONGHUXY, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHIFOUYJRGC==null?"null":SHIFOUYJRGC, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(DANSHUANGDY==null?"null":DANSHUANGDY, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YEWUSLJSSJ==null?"null":"" + YEWUSLJSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YEKUOSLSJ==null?"null":"" + YEKUOSLSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(XIANCHANGKCKSSJ==null?"null":"" + XIANCHANGKCKSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(XIANCHANGKCJSSJ==null?"null":"" + XIANCHANGKCJSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(XIANCHANGKCSC==null?"null":XIANCHANGKCSC.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANFASCKSSJ==null?"null":"" + GONGDIANFASCKSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANFASCJSSJ==null?"null":"" + GONGDIANFASCJSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANFASCSC==null?"null":GONGDIANFASCSC.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANFADFKHSJ==null?"null":"" + GONGDIANFADFKHSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANFADFKHSC==null?"null":GONGDIANFADFKHSC.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANFADFSJSFCB==null?"null":GONGDIANFADFSJSFCB, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANFAYHQRSJ==null?"null":"" + GONGDIANFAYHQRSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHOUDIANGCSJZLTJSJ==null?"null":"" + SHOUDIANGCSJZLTJSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHOUDIANGCSJZLSCJSSJ==null?"null":"" + SHOUDIANGCSJZLSCJSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHOUDIANGCSJZLSHSC==null?"null":SHOUDIANGCSJZLSHSC.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHEJIZLSHSJSFCB==null?"null":SHEJIZLSHSJSFCB, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHOUDIANGCZJJCKSSJ==null?"null":"" + SHOUDIANGCZJJCKSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHOUDIANGCZJJCJSSJ==null?"null":"" + SHOUDIANGCZJJCJSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHOUDIANGCZJJCSC==null?"null":SHOUDIANGCZJJCSC.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ZHONGJIANJCSJSFCB==null?"null":ZHONGJIANJCSJSFCB, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHOUDIANGCJGYSKSSJ==null?"null":"" + SHOUDIANGCJGYSKSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHOUDIANGCJGYSJSSJ==null?"null":"" + SHOUDIANGCJGYSJSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SHOUDIANGCJGYSSC==null?"null":SHOUDIANGCJGYSSC.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(JUNGONGYSSJSFCB==null?"null":JUNGONGYSSJSFCB, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GAOYAGYDHTQDSJ==null?"null":"" + GAOYAGYDHTQDSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ZHUANGBIAOKSSJ==null?"null":"" + ZHUANGBIAOKSSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ZHUANGBIAOWCSJ==null?"null":"" + ZHUANGBIAOWCSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(QIANRUSDWCSJ==null?"null":"" + QIANRUSDWCSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ZHUANGBIAOJDSC==null?"null":ZHUANGBIAOJDSC.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ZHUANGBIAOJDSJSFCB==null?"null":ZHUANGBIAOJDSJSFCB, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YEKUOFWDDDSC==null?"null":YEKUOFWDDDSC.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YEKUOGDWCSJ==null?"null":"" + YEKUOGDWCSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANFADFKHHFWCSJ==null?"null":"" + GONGDIANFADFKHHFWCSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ZHUANGBIAOJDKHHFWCSJ==null?"null":"" + ZHUANGBIAOJDKHHFWCSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(GONGDIANFADFHJKHHFMYD==null?"null":GONGDIANFADFHJKHHFMYD, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ZHUANGBIAOJDHJHFKHMYD==null?"null":ZHUANGBIAOJDHJHFKHMYD, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(YEKUOLCLX==null?"null":YEKUOLCLX, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(EXT_VALUE1==null?"null":EXT_VALUE1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(EXT_VALUE2==null?"null":EXT_VALUE2, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(EXT_VALUE3==null?"null":EXT_VALUE3, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(EXT_VALUE4==null?"null":EXT_VALUE4, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(EXT_VALUE5==null?"null":EXT_VALUE5, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(TIME_STAMP==null?"null":"" + TIME_STAMP, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(TUISONGSJ==null?"null":TUISONGSJ, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(VER_NO==null?"null":VER_NO.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ALL_TIME==null?"null":ALL_TIME.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(PLAN_REPLY_TIME==null?"null":PLAN_REPLY_TIME.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SCENE_RESEARCH_TIME==null?"null":SCENE_RESEARCH_TIME.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(PLAN_CHECK_TIME==null?"null":PLAN_CHECK_TIME.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(DATA_CHECK_TIME==null?"null":DATA_CHECK_TIME.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(PROCESS_CHECK_TIME==null?"null":PROCESS_CHECK_TIME.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(COMPLETED_ACCEPT_TIME==null?"null":COMPLETED_ACCEPT_TIME.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(SEND_ELE_TIME==null?"null":SEND_ELE_TIME.toPlainString(), delimiters));
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
    if (__cur_str.equals("null")) { this.YEKUOGDH = null; } else {
      this.YEKUOGDH = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.GONGDANCJRQ = null; } else {
      this.GONGDANCJRQ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.GONGDIANDW_DS = null; } else {
      this.GONGDIANDW_DS = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.GONGDIANDW_XQ = null; } else {
      this.GONGDIANDW_XQ = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.YONGHUMC = null; } else {
      this.YONGHUMC = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.YONGHUBZRL = null; } else {
      this.YONGHUBZRL = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.YONGHUYYRL = null; } else {
      this.YONGHUYYRL = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.DIANYADJ = null; } else {
      this.DIANYADJ = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.YONGDIANLB = null; } else {
      this.YONGDIANLB = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.YONGHUXY = null; } else {
      this.YONGHUXY = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.SHIFOUYJRGC = null; } else {
      this.SHIFOUYJRGC = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.DANSHUANGDY = null; } else {
      this.DANSHUANGDY = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.YEWUSLJSSJ = null; } else {
      this.YEWUSLJSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.YEKUOSLSJ = null; } else {
      this.YEKUOSLSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.XIANCHANGKCKSSJ = null; } else {
      this.XIANCHANGKCKSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.XIANCHANGKCJSSJ = null; } else {
      this.XIANCHANGKCJSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.XIANCHANGKCSC = null; } else {
      this.XIANCHANGKCSC = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.GONGDIANFASCKSSJ = null; } else {
      this.GONGDIANFASCKSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.GONGDIANFASCJSSJ = null; } else {
      this.GONGDIANFASCJSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.GONGDIANFASCSC = null; } else {
      this.GONGDIANFASCSC = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.GONGDIANFADFKHSJ = null; } else {
      this.GONGDIANFADFKHSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.GONGDIANFADFKHSC = null; } else {
      this.GONGDIANFADFKHSC = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.GONGDIANFADFSJSFCB = null; } else {
      this.GONGDIANFADFSJSFCB = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.GONGDIANFAYHQRSJ = null; } else {
      this.GONGDIANFAYHQRSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SHOUDIANGCSJZLTJSJ = null; } else {
      this.SHOUDIANGCSJZLTJSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SHOUDIANGCSJZLSCJSSJ = null; } else {
      this.SHOUDIANGCSJZLSCJSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SHOUDIANGCSJZLSHSC = null; } else {
      this.SHOUDIANGCSJZLSHSC = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.SHEJIZLSHSJSFCB = null; } else {
      this.SHEJIZLSHSJSFCB = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SHOUDIANGCZJJCKSSJ = null; } else {
      this.SHOUDIANGCZJJCKSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SHOUDIANGCZJJCJSSJ = null; } else {
      this.SHOUDIANGCZJJCJSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SHOUDIANGCZJJCSC = null; } else {
      this.SHOUDIANGCZJJCSC = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ZHONGJIANJCSJSFCB = null; } else {
      this.ZHONGJIANJCSJSFCB = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SHOUDIANGCJGYSKSSJ = null; } else {
      this.SHOUDIANGCJGYSKSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SHOUDIANGCJGYSJSSJ = null; } else {
      this.SHOUDIANGCJGYSJSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SHOUDIANGCJGYSSC = null; } else {
      this.SHOUDIANGCJGYSSC = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.JUNGONGYSSJSFCB = null; } else {
      this.JUNGONGYSSJSFCB = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.GAOYAGYDHTQDSJ = null; } else {
      this.GAOYAGYDHTQDSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ZHUANGBIAOKSSJ = null; } else {
      this.ZHUANGBIAOKSSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ZHUANGBIAOWCSJ = null; } else {
      this.ZHUANGBIAOWCSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.QIANRUSDWCSJ = null; } else {
      this.QIANRUSDWCSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ZHUANGBIAOJDSC = null; } else {
      this.ZHUANGBIAOJDSC = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ZHUANGBIAOJDSJSFCB = null; } else {
      this.ZHUANGBIAOJDSJSFCB = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.YEKUOFWDDDSC = null; } else {
      this.YEKUOFWDDDSC = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.YEKUOGDWCSJ = null; } else {
      this.YEKUOGDWCSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.GONGDIANFADFKHHFWCSJ = null; } else {
      this.GONGDIANFADFKHHFWCSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ZHUANGBIAOJDKHHFWCSJ = null; } else {
      this.ZHUANGBIAOJDKHHFWCSJ = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.GONGDIANFADFHJKHHFMYD = null; } else {
      this.GONGDIANFADFHJKHHFMYD = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ZHUANGBIAOJDHJHFKHMYD = null; } else {
      this.ZHUANGBIAOJDHJHFKHMYD = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.YEKUOLCLX = null; } else {
      this.YEKUOLCLX = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.EXT_VALUE1 = null; } else {
      this.EXT_VALUE1 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.EXT_VALUE2 = null; } else {
      this.EXT_VALUE2 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.EXT_VALUE3 = null; } else {
      this.EXT_VALUE3 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.EXT_VALUE4 = null; } else {
      this.EXT_VALUE4 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.EXT_VALUE5 = null; } else {
      this.EXT_VALUE5 = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.TIME_STAMP = null; } else {
      this.TIME_STAMP = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.TUISONGSJ = null; } else {
      this.TUISONGSJ = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.VER_NO = null; } else {
      this.VER_NO = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ALL_TIME = null; } else {
      this.ALL_TIME = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.PLAN_REPLY_TIME = null; } else {
      this.PLAN_REPLY_TIME = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SCENE_RESEARCH_TIME = null; } else {
      this.SCENE_RESEARCH_TIME = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.PLAN_CHECK_TIME = null; } else {
      this.PLAN_CHECK_TIME = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.DATA_CHECK_TIME = null; } else {
      this.DATA_CHECK_TIME = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.PROCESS_CHECK_TIME = null; } else {
      this.PROCESS_CHECK_TIME = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.COMPLETED_ACCEPT_TIME = null; } else {
      this.COMPLETED_ACCEPT_TIME = new java.math.BigDecimal(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.SEND_ELE_TIME = null; } else {
      this.SEND_ELE_TIME = new java.math.BigDecimal(__cur_str);
    }

  }

  public Object clone() throws CloneNotSupportedException {
    YKBZ_DETAIL o = (YKBZ_DETAIL) super.clone();
    o.GONGDANCJRQ = (o.GONGDANCJRQ != null) ? (java.sql.Timestamp) o.GONGDANCJRQ.clone() : null;
    o.YEWUSLJSSJ = (o.YEWUSLJSSJ != null) ? (java.sql.Timestamp) o.YEWUSLJSSJ.clone() : null;
    o.YEKUOSLSJ = (o.YEKUOSLSJ != null) ? (java.sql.Timestamp) o.YEKUOSLSJ.clone() : null;
    o.XIANCHANGKCKSSJ = (o.XIANCHANGKCKSSJ != null) ? (java.sql.Timestamp) o.XIANCHANGKCKSSJ.clone() : null;
    o.XIANCHANGKCJSSJ = (o.XIANCHANGKCJSSJ != null) ? (java.sql.Timestamp) o.XIANCHANGKCJSSJ.clone() : null;
    o.GONGDIANFASCKSSJ = (o.GONGDIANFASCKSSJ != null) ? (java.sql.Timestamp) o.GONGDIANFASCKSSJ.clone() : null;
    o.GONGDIANFASCJSSJ = (o.GONGDIANFASCJSSJ != null) ? (java.sql.Timestamp) o.GONGDIANFASCJSSJ.clone() : null;
    o.GONGDIANFADFKHSJ = (o.GONGDIANFADFKHSJ != null) ? (java.sql.Timestamp) o.GONGDIANFADFKHSJ.clone() : null;
    o.GONGDIANFAYHQRSJ = (o.GONGDIANFAYHQRSJ != null) ? (java.sql.Timestamp) o.GONGDIANFAYHQRSJ.clone() : null;
    o.SHOUDIANGCSJZLTJSJ = (o.SHOUDIANGCSJZLTJSJ != null) ? (java.sql.Timestamp) o.SHOUDIANGCSJZLTJSJ.clone() : null;
    o.SHOUDIANGCSJZLSCJSSJ = (o.SHOUDIANGCSJZLSCJSSJ != null) ? (java.sql.Timestamp) o.SHOUDIANGCSJZLSCJSSJ.clone() : null;
    o.SHOUDIANGCZJJCKSSJ = (o.SHOUDIANGCZJJCKSSJ != null) ? (java.sql.Timestamp) o.SHOUDIANGCZJJCKSSJ.clone() : null;
    o.SHOUDIANGCZJJCJSSJ = (o.SHOUDIANGCZJJCJSSJ != null) ? (java.sql.Timestamp) o.SHOUDIANGCZJJCJSSJ.clone() : null;
    o.SHOUDIANGCJGYSKSSJ = (o.SHOUDIANGCJGYSKSSJ != null) ? (java.sql.Timestamp) o.SHOUDIANGCJGYSKSSJ.clone() : null;
    o.SHOUDIANGCJGYSJSSJ = (o.SHOUDIANGCJGYSJSSJ != null) ? (java.sql.Timestamp) o.SHOUDIANGCJGYSJSSJ.clone() : null;
    o.GAOYAGYDHTQDSJ = (o.GAOYAGYDHTQDSJ != null) ? (java.sql.Timestamp) o.GAOYAGYDHTQDSJ.clone() : null;
    o.ZHUANGBIAOKSSJ = (o.ZHUANGBIAOKSSJ != null) ? (java.sql.Timestamp) o.ZHUANGBIAOKSSJ.clone() : null;
    o.ZHUANGBIAOWCSJ = (o.ZHUANGBIAOWCSJ != null) ? (java.sql.Timestamp) o.ZHUANGBIAOWCSJ.clone() : null;
    o.QIANRUSDWCSJ = (o.QIANRUSDWCSJ != null) ? (java.sql.Timestamp) o.QIANRUSDWCSJ.clone() : null;
    o.YEKUOGDWCSJ = (o.YEKUOGDWCSJ != null) ? (java.sql.Timestamp) o.YEKUOGDWCSJ.clone() : null;
    o.GONGDIANFADFKHHFWCSJ = (o.GONGDIANFADFKHHFWCSJ != null) ? (java.sql.Timestamp) o.GONGDIANFADFKHHFWCSJ.clone() : null;
    o.ZHUANGBIAOJDKHHFWCSJ = (o.ZHUANGBIAOJDKHHFWCSJ != null) ? (java.sql.Timestamp) o.ZHUANGBIAOJDKHHFWCSJ.clone() : null;
    o.TIME_STAMP = (o.TIME_STAMP != null) ? (java.sql.Timestamp) o.TIME_STAMP.clone() : null;
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("YEKUOGDH", this.YEKUOGDH);
    __sqoop$field_map.put("GONGDANCJRQ", this.GONGDANCJRQ);
    __sqoop$field_map.put("GONGDIANDW_DS", this.GONGDIANDW_DS);
    __sqoop$field_map.put("GONGDIANDW_XQ", this.GONGDIANDW_XQ);
    __sqoop$field_map.put("YONGHUMC", this.YONGHUMC);
    __sqoop$field_map.put("YONGHUBZRL", this.YONGHUBZRL);
    __sqoop$field_map.put("YONGHUYYRL", this.YONGHUYYRL);
    __sqoop$field_map.put("DIANYADJ", this.DIANYADJ);
    __sqoop$field_map.put("YONGDIANLB", this.YONGDIANLB);
    __sqoop$field_map.put("YONGHUXY", this.YONGHUXY);
    __sqoop$field_map.put("SHIFOUYJRGC", this.SHIFOUYJRGC);
    __sqoop$field_map.put("DANSHUANGDY", this.DANSHUANGDY);
    __sqoop$field_map.put("YEWUSLJSSJ", this.YEWUSLJSSJ);
    __sqoop$field_map.put("YEKUOSLSJ", this.YEKUOSLSJ);
    __sqoop$field_map.put("XIANCHANGKCKSSJ", this.XIANCHANGKCKSSJ);
    __sqoop$field_map.put("XIANCHANGKCJSSJ", this.XIANCHANGKCJSSJ);
    __sqoop$field_map.put("XIANCHANGKCSC", this.XIANCHANGKCSC);
    __sqoop$field_map.put("GONGDIANFASCKSSJ", this.GONGDIANFASCKSSJ);
    __sqoop$field_map.put("GONGDIANFASCJSSJ", this.GONGDIANFASCJSSJ);
    __sqoop$field_map.put("GONGDIANFASCSC", this.GONGDIANFASCSC);
    __sqoop$field_map.put("GONGDIANFADFKHSJ", this.GONGDIANFADFKHSJ);
    __sqoop$field_map.put("GONGDIANFADFKHSC", this.GONGDIANFADFKHSC);
    __sqoop$field_map.put("GONGDIANFADFSJSFCB", this.GONGDIANFADFSJSFCB);
    __sqoop$field_map.put("GONGDIANFAYHQRSJ", this.GONGDIANFAYHQRSJ);
    __sqoop$field_map.put("SHOUDIANGCSJZLTJSJ", this.SHOUDIANGCSJZLTJSJ);
    __sqoop$field_map.put("SHOUDIANGCSJZLSCJSSJ", this.SHOUDIANGCSJZLSCJSSJ);
    __sqoop$field_map.put("SHOUDIANGCSJZLSHSC", this.SHOUDIANGCSJZLSHSC);
    __sqoop$field_map.put("SHEJIZLSHSJSFCB", this.SHEJIZLSHSJSFCB);
    __sqoop$field_map.put("SHOUDIANGCZJJCKSSJ", this.SHOUDIANGCZJJCKSSJ);
    __sqoop$field_map.put("SHOUDIANGCZJJCJSSJ", this.SHOUDIANGCZJJCJSSJ);
    __sqoop$field_map.put("SHOUDIANGCZJJCSC", this.SHOUDIANGCZJJCSC);
    __sqoop$field_map.put("ZHONGJIANJCSJSFCB", this.ZHONGJIANJCSJSFCB);
    __sqoop$field_map.put("SHOUDIANGCJGYSKSSJ", this.SHOUDIANGCJGYSKSSJ);
    __sqoop$field_map.put("SHOUDIANGCJGYSJSSJ", this.SHOUDIANGCJGYSJSSJ);
    __sqoop$field_map.put("SHOUDIANGCJGYSSC", this.SHOUDIANGCJGYSSC);
    __sqoop$field_map.put("JUNGONGYSSJSFCB", this.JUNGONGYSSJSFCB);
    __sqoop$field_map.put("GAOYAGYDHTQDSJ", this.GAOYAGYDHTQDSJ);
    __sqoop$field_map.put("ZHUANGBIAOKSSJ", this.ZHUANGBIAOKSSJ);
    __sqoop$field_map.put("ZHUANGBIAOWCSJ", this.ZHUANGBIAOWCSJ);
    __sqoop$field_map.put("QIANRUSDWCSJ", this.QIANRUSDWCSJ);
    __sqoop$field_map.put("ZHUANGBIAOJDSC", this.ZHUANGBIAOJDSC);
    __sqoop$field_map.put("ZHUANGBIAOJDSJSFCB", this.ZHUANGBIAOJDSJSFCB);
    __sqoop$field_map.put("YEKUOFWDDDSC", this.YEKUOFWDDDSC);
    __sqoop$field_map.put("YEKUOGDWCSJ", this.YEKUOGDWCSJ);
    __sqoop$field_map.put("GONGDIANFADFKHHFWCSJ", this.GONGDIANFADFKHHFWCSJ);
    __sqoop$field_map.put("ZHUANGBIAOJDKHHFWCSJ", this.ZHUANGBIAOJDKHHFWCSJ);
    __sqoop$field_map.put("GONGDIANFADFHJKHHFMYD", this.GONGDIANFADFHJKHHFMYD);
    __sqoop$field_map.put("ZHUANGBIAOJDHJHFKHMYD", this.ZHUANGBIAOJDHJHFKHMYD);
    __sqoop$field_map.put("YEKUOLCLX", this.YEKUOLCLX);
    __sqoop$field_map.put("EXT_VALUE1", this.EXT_VALUE1);
    __sqoop$field_map.put("EXT_VALUE2", this.EXT_VALUE2);
    __sqoop$field_map.put("EXT_VALUE3", this.EXT_VALUE3);
    __sqoop$field_map.put("EXT_VALUE4", this.EXT_VALUE4);
    __sqoop$field_map.put("EXT_VALUE5", this.EXT_VALUE5);
    __sqoop$field_map.put("TIME_STAMP", this.TIME_STAMP);
    __sqoop$field_map.put("TUISONGSJ", this.TUISONGSJ);
    __sqoop$field_map.put("VER_NO", this.VER_NO);
    __sqoop$field_map.put("ALL_TIME", this.ALL_TIME);
    __sqoop$field_map.put("PLAN_REPLY_TIME", this.PLAN_REPLY_TIME);
    __sqoop$field_map.put("SCENE_RESEARCH_TIME", this.SCENE_RESEARCH_TIME);
    __sqoop$field_map.put("PLAN_CHECK_TIME", this.PLAN_CHECK_TIME);
    __sqoop$field_map.put("DATA_CHECK_TIME", this.DATA_CHECK_TIME);
    __sqoop$field_map.put("PROCESS_CHECK_TIME", this.PROCESS_CHECK_TIME);
    __sqoop$field_map.put("COMPLETED_ACCEPT_TIME", this.COMPLETED_ACCEPT_TIME);
    __sqoop$field_map.put("SEND_ELE_TIME", this.SEND_ELE_TIME);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("YEKUOGDH".equals(__fieldName)) {
      this.YEKUOGDH = (String) __fieldVal;
    }
    else    if ("GONGDANCJRQ".equals(__fieldName)) {
      this.GONGDANCJRQ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("GONGDIANDW_DS".equals(__fieldName)) {
      this.GONGDIANDW_DS = (String) __fieldVal;
    }
    else    if ("GONGDIANDW_XQ".equals(__fieldName)) {
      this.GONGDIANDW_XQ = (String) __fieldVal;
    }
    else    if ("YONGHUMC".equals(__fieldName)) {
      this.YONGHUMC = (String) __fieldVal;
    }
    else    if ("YONGHUBZRL".equals(__fieldName)) {
      this.YONGHUBZRL = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("YONGHUYYRL".equals(__fieldName)) {
      this.YONGHUYYRL = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("DIANYADJ".equals(__fieldName)) {
      this.DIANYADJ = (String) __fieldVal;
    }
    else    if ("YONGDIANLB".equals(__fieldName)) {
      this.YONGDIANLB = (String) __fieldVal;
    }
    else    if ("YONGHUXY".equals(__fieldName)) {
      this.YONGHUXY = (String) __fieldVal;
    }
    else    if ("SHIFOUYJRGC".equals(__fieldName)) {
      this.SHIFOUYJRGC = (String) __fieldVal;
    }
    else    if ("DANSHUANGDY".equals(__fieldName)) {
      this.DANSHUANGDY = (String) __fieldVal;
    }
    else    if ("YEWUSLJSSJ".equals(__fieldName)) {
      this.YEWUSLJSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("YEKUOSLSJ".equals(__fieldName)) {
      this.YEKUOSLSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("XIANCHANGKCKSSJ".equals(__fieldName)) {
      this.XIANCHANGKCKSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("XIANCHANGKCJSSJ".equals(__fieldName)) {
      this.XIANCHANGKCJSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("XIANCHANGKCSC".equals(__fieldName)) {
      this.XIANCHANGKCSC = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("GONGDIANFASCKSSJ".equals(__fieldName)) {
      this.GONGDIANFASCKSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("GONGDIANFASCJSSJ".equals(__fieldName)) {
      this.GONGDIANFASCJSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("GONGDIANFASCSC".equals(__fieldName)) {
      this.GONGDIANFASCSC = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("GONGDIANFADFKHSJ".equals(__fieldName)) {
      this.GONGDIANFADFKHSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("GONGDIANFADFKHSC".equals(__fieldName)) {
      this.GONGDIANFADFKHSC = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("GONGDIANFADFSJSFCB".equals(__fieldName)) {
      this.GONGDIANFADFSJSFCB = (String) __fieldVal;
    }
    else    if ("GONGDIANFAYHQRSJ".equals(__fieldName)) {
      this.GONGDIANFAYHQRSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("SHOUDIANGCSJZLTJSJ".equals(__fieldName)) {
      this.SHOUDIANGCSJZLTJSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("SHOUDIANGCSJZLSCJSSJ".equals(__fieldName)) {
      this.SHOUDIANGCSJZLSCJSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("SHOUDIANGCSJZLSHSC".equals(__fieldName)) {
      this.SHOUDIANGCSJZLSHSC = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("SHEJIZLSHSJSFCB".equals(__fieldName)) {
      this.SHEJIZLSHSJSFCB = (String) __fieldVal;
    }
    else    if ("SHOUDIANGCZJJCKSSJ".equals(__fieldName)) {
      this.SHOUDIANGCZJJCKSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("SHOUDIANGCZJJCJSSJ".equals(__fieldName)) {
      this.SHOUDIANGCZJJCJSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("SHOUDIANGCZJJCSC".equals(__fieldName)) {
      this.SHOUDIANGCZJJCSC = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("ZHONGJIANJCSJSFCB".equals(__fieldName)) {
      this.ZHONGJIANJCSJSFCB = (String) __fieldVal;
    }
    else    if ("SHOUDIANGCJGYSKSSJ".equals(__fieldName)) {
      this.SHOUDIANGCJGYSKSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("SHOUDIANGCJGYSJSSJ".equals(__fieldName)) {
      this.SHOUDIANGCJGYSJSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("SHOUDIANGCJGYSSC".equals(__fieldName)) {
      this.SHOUDIANGCJGYSSC = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("JUNGONGYSSJSFCB".equals(__fieldName)) {
      this.JUNGONGYSSJSFCB = (String) __fieldVal;
    }
    else    if ("GAOYAGYDHTQDSJ".equals(__fieldName)) {
      this.GAOYAGYDHTQDSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("ZHUANGBIAOKSSJ".equals(__fieldName)) {
      this.ZHUANGBIAOKSSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("ZHUANGBIAOWCSJ".equals(__fieldName)) {
      this.ZHUANGBIAOWCSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("QIANRUSDWCSJ".equals(__fieldName)) {
      this.QIANRUSDWCSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("ZHUANGBIAOJDSC".equals(__fieldName)) {
      this.ZHUANGBIAOJDSC = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("ZHUANGBIAOJDSJSFCB".equals(__fieldName)) {
      this.ZHUANGBIAOJDSJSFCB = (String) __fieldVal;
    }
    else    if ("YEKUOFWDDDSC".equals(__fieldName)) {
      this.YEKUOFWDDDSC = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("YEKUOGDWCSJ".equals(__fieldName)) {
      this.YEKUOGDWCSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("GONGDIANFADFKHHFWCSJ".equals(__fieldName)) {
      this.GONGDIANFADFKHHFWCSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("ZHUANGBIAOJDKHHFWCSJ".equals(__fieldName)) {
      this.ZHUANGBIAOJDKHHFWCSJ = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("GONGDIANFADFHJKHHFMYD".equals(__fieldName)) {
      this.GONGDIANFADFHJKHHFMYD = (String) __fieldVal;
    }
    else    if ("ZHUANGBIAOJDHJHFKHMYD".equals(__fieldName)) {
      this.ZHUANGBIAOJDHJHFKHMYD = (String) __fieldVal;
    }
    else    if ("YEKUOLCLX".equals(__fieldName)) {
      this.YEKUOLCLX = (String) __fieldVal;
    }
    else    if ("EXT_VALUE1".equals(__fieldName)) {
      this.EXT_VALUE1 = (String) __fieldVal;
    }
    else    if ("EXT_VALUE2".equals(__fieldName)) {
      this.EXT_VALUE2 = (String) __fieldVal;
    }
    else    if ("EXT_VALUE3".equals(__fieldName)) {
      this.EXT_VALUE3 = (String) __fieldVal;
    }
    else    if ("EXT_VALUE4".equals(__fieldName)) {
      this.EXT_VALUE4 = (String) __fieldVal;
    }
    else    if ("EXT_VALUE5".equals(__fieldName)) {
      this.EXT_VALUE5 = (String) __fieldVal;
    }
    else    if ("TIME_STAMP".equals(__fieldName)) {
      this.TIME_STAMP = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("TUISONGSJ".equals(__fieldName)) {
      this.TUISONGSJ = (String) __fieldVal;
    }
    else    if ("VER_NO".equals(__fieldName)) {
      this.VER_NO = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("ALL_TIME".equals(__fieldName)) {
      this.ALL_TIME = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("PLAN_REPLY_TIME".equals(__fieldName)) {
      this.PLAN_REPLY_TIME = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("SCENE_RESEARCH_TIME".equals(__fieldName)) {
      this.SCENE_RESEARCH_TIME = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("PLAN_CHECK_TIME".equals(__fieldName)) {
      this.PLAN_CHECK_TIME = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("DATA_CHECK_TIME".equals(__fieldName)) {
      this.DATA_CHECK_TIME = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("PROCESS_CHECK_TIME".equals(__fieldName)) {
      this.PROCESS_CHECK_TIME = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("COMPLETED_ACCEPT_TIME".equals(__fieldName)) {
      this.COMPLETED_ACCEPT_TIME = (java.math.BigDecimal) __fieldVal;
    }
    else    if ("SEND_ELE_TIME".equals(__fieldName)) {
      this.SEND_ELE_TIME = (java.math.BigDecimal) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
