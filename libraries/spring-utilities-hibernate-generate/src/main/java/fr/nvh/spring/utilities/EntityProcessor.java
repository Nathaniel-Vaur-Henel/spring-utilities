/*
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * If you happen to meet one of the copyright holders in a bar you are obligated
 * to buy them one pint of beer.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fr.nvh.spring.utilities;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import jakarta.persistence.Entity;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.util.Collections;
import java.util.Set;

@AutoService(Processor.class)
public class EntityProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "EntityProcessor initialized");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Entity.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return true;
        }
        try {
            // Only one annotation is managed in this processor, so the annotations set has only one element.
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Entity.class);
            ElementFilter.typesIn(elements).forEach(this::manageElement);
        } catch (Exception t) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, t.getMessage());
            return false;
        }
        return true;
    }

    private void manageElement(TypeElement typeElement) {
        TypeSpec.Builder constantClass =
                TypeSpec.interfaceBuilder(typeElement.getQualifiedName() + "_").addModifiers(Modifier.PUBLIC);

        typeElement.getEnclosedElements().stream()
                .filter(element -> element.getKind().isField())
                .forEach(element -> {
                    String fieldName = element.getSimpleName().toString();
                    String constantName = camelToSnake(fieldName) + "_";
                    FieldSpec field = FieldSpec.builder(String.class, constantName)
                            .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                            .initializer(fieldName)
                            .build();
                    constantClass.addField(field);
                });

        constantClass.build();
        //        for (Element element : typeElement.getEnclosedElements()) {
        //            System.out.println(element.getSimpleName());
        //        }
        //            .addField(name)
        //            .addMethod(MethodSpec
        //                .methodBuilder("getName")
        //                .addModifiers(Modifier.PUBLIC)
        //                .returns(String.class)
        //                .addStatement("return this.name")
        //                .build())
        //            .addMethod(MethodSpec
        //                .methodBuilder("setName")
        //                .addParameter(String.class, "name")
        //                .addModifiers(Modifier.PUBLIC)
        //                .returns(String.class)
        //                .addStatement("this.name = name")
        //                .build())
        //            .addMethod(sumOfTen)
        //            .build();

        typeElement.getTypeParameters();
    }

    public static String camelToSnake(String str) {
        String regex = "([a-z])([A-Z]+)";
        return str.replaceAll(regex, "$1_$2").toUpperCase();
    }
}
