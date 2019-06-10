package 笔记.pojo;


import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SimpleNote
 * @Description 简单节点
 * @Author shnstt
 * @Date 2019/6/14 15:50
 * @Version 1.0
 **/
public class SimpleNote {
    private String value;
    private SimpleNote parent;
    private List<SimpleNote> children;
    private boolean checked = false;


    public SimpleNote(@NotNull String value) {
        this.value = value;
        this.parent=null;
        this.children =null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SimpleNote getParent() {
        return parent;
    }

    public void setParent(SimpleNote parent) {
        this.parent = parent;
    }

    public List<SimpleNote> getChildren() {
        return children==null?children=new ArrayList<>(16):children;
    }

    public void setChildren(List<SimpleNote> children) {
        this.children = children;
    }

    public boolean equals(@org.jetbrains.annotations.NotNull SimpleNote other) {
        return this.value.equals(other.value);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
