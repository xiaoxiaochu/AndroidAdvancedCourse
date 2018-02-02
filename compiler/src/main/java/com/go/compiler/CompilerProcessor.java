package com.go.compiler;

import com.go.library.ZyaoAnnotation;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;



/**
 * Created by go on 2018/2/2.
 */


@AutoService(Processor.class)
public class CompilerProcessor extends AbstractProcessor {

    private static final String SUFFIX = "$$Chu";

    private Types mTypeUtils;
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        // 我提交的内容到哪去了

    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(ZyaoAnnotation.class)) {
            System.out.println("------------------------------");

            if (element.getKind() == ElementKind.CLASS) {

                TypeElement typeElement = (TypeElement) element;

                System.out.println(typeElement.getSimpleName());

             //   System.out.println(typeElement.getAnnotation(AutoParcel.class).value());

                analysisAnnotated(element);
            }
            System.out.println("------------------------------");
        }



        //如果没有其他处理器要处理这个注解，就返回true否则falses
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes()
    {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(ZyaoAnnotation.class.getCanonicalName());
        return annotations;
    }





    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }


    private void analysisAnnotated(Element classElement)
    {
        ZyaoAnnotation annotation = classElement.getAnnotation(ZyaoAnnotation.class);
        String name = annotation.name();
        String text = annotation.text();

//        TypeElement superClassName = mElementUtils.getTypeElement(name);
        String newClassName = name + SUFFIX;

        StringBuilder builder = new StringBuilder()
                .append("package com.go.library;\n\n")
                .append("public class ")
                .append(newClassName)
                .append(" {\n\n") // open class
                .append("\tpublic String getMessage() {\n") // open method
                .append("\t\treturn \"");

        // this is appending to the return statement
        builder.append(text).append(name).append(" !\\n");


        builder.append("\";\n") // end return
                .append("\t}\n") // close method
                .append("}\n"); // close class


        try { // write the file
            JavaFileObject source = mFiler.createSourceFile("com.go.library."+newClassName);
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }

     //   info(">>> analysisAnnotated is finish... <<<");

    }

}
