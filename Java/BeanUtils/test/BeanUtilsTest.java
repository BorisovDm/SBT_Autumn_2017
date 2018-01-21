import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BeanUtilsTest {
    @Test
    public void testBeanUtilsAssign() {
        A from = new A(3, 10, 45.345, "str1", 345678L, -56789);
        B to = new B(453, 34, 4005.35, "str2", 3408, "hello");
        new BeanUtils().assign(to, from);

        assertNotEquals(from.getField1(), to.getField1());
        assertEquals(from.getField2(), to.getField2());
        assertEquals(from.getField3(), to.getField3());
        assertEquals(from.getField4(), to.getField4());
        assertNotEquals(from.getField5(), to.getField5());
        assertNotEquals(from.getField6(), to.getField6());
    }
}

class A {
    private Integer field1;
    private Integer field2;
    private Double field3;
    private String field4;
    private Long field5;
    private Integer field6;

    A(Integer field1, Integer field2, Double field3, String field4, Long field5, Integer field6) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
        this.field6 = field6;
    }

    public Integer getField1() {
        return field1;
    }

    public Integer getField2() {
        return field2;
    }

    public Double getField3() {
        return field3;
    }

    public String getField4() {
        return field4;
    }

    public Long getField5() {
        return field5;
    }

    public Integer getField6() {
        return field6;
    }
}

class B {
    private Integer field1;
    private Integer field2;
    private Number field3;
    private String field4;
    private Integer field5;
    private String field6;

    B(Integer field1, Integer field2, Number field3, String field4, Integer field5, String field6) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
        this.field6 = field6;
    }

    public Integer getField1() {
        return field1;
    }

    public Integer getField2() {
        return field2;
    }

    public Number getField3() {
        return field3;
    }

    public String getField4() {
        return field4;
    }

    public Integer getField5() {
        return field5;
    }

    public String getField6() {
        return field6;
    }

    public void setField2(Integer field2) {
        this.field2 = field2;
    }

    public void setField3(Number field3) {
        this.field3 = field3;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public void setField5(Integer field5) {
        this.field5 = field5;
    }

    public void setField6(String field6) {
        this.field6 = field6;
    }
}