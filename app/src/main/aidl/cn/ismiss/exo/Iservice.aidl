// Iservice.aidl
package cn.ismiss.exo;

// Declare any non-default types here with import statements

interface Iservice {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
 boolean callPay(String name, String pwd, int money);
}