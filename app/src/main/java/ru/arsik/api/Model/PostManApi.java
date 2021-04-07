package ru.arsik.api.Model;

import com.google.gson.annotations.SerializedName;

public class PostManApi {
    @SerializedName("args")
    public Args args;

    public class Args {
        @SerializedName("foo1")
        public String foo1;
        @SerializedName("foo2")
        public String foo2;
        @SerializedName("foo3")
        public String foo3;

        public String getFoo1() {
            return foo1;
        }

        public void setFoo1(String foo1) {
            this.foo1 = foo1;
        }

        public String getFoo2() {
            return foo2;
        }

        public void setFoo2(String foo2) {
            this.foo2 = foo2;
        }

        public String getFoo3() {
            return foo3;
        }

        public void setFoo3(String foo3) {
            this.foo3 = foo3;
        }
    }
}
