package org.apache.tiles.template;

import java.util.Stack;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.mgmt.MutableTilesContainer;

public class DefinitionModel {

    public void start(Stack<Object> composeStack, String name, String template,
            String role, String extendsParam, String preparer) {
        Definition definition = createDefinition(name, template, role,
                extendsParam, preparer);
        composeStack.push(definition);
    }
    
    public void end(MutableTilesContainer container,
            Stack<Object> composeStack, String name, Object... requestItems) {
        Definition definition = (Definition) composeStack.pop();
        registerDefinition(definition, container, composeStack, requestItems);
    }
    
    public void execute(MutableTilesContainer container,
            Stack<Object> composeStack, String name, String template,
            String role, String extendsParam, String preparer,
            Object... requestItems) {
        Definition definition = createDefinition(name, template, role,
                extendsParam, preparer);
        registerDefinition(definition, container, composeStack, requestItems);
    }

    private Definition createDefinition(String name, String template,
            String role, String extendsParam, String preparer) {
        Definition definition = new Definition();
        definition.setName(name);
        Attribute templateAttribute = Attribute
                .createTemplateAttribute(template);
        templateAttribute.setRole(role);
        definition.setTemplateAttribute(templateAttribute);
        definition.setExtends(extendsParam);
        definition.setPreparer(preparer);
        return definition;
    }

    private void registerDefinition(Definition definition,
            MutableTilesContainer container, Stack<Object> composeStack,
            Object... requestItems) {
        container.register(definition, requestItems);
        
        if (composeStack.isEmpty()) {
            return;
        }
        
        Object obj = composeStack.peek();
        if (obj instanceof Attribute) {
            Attribute attribute = (Attribute) obj;
            attribute.setValue(definition.getName());
            if (attribute.getRenderer() == null) {
                attribute.setRenderer("definition");
            }
        }
    }
}