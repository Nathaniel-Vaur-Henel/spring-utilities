package io.github.nathaniel.vaur.henel.spring.utilities.conf;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * This {@link org.hibernate.boot.model.naming.PhysicalNamingStrategy} class allow to remove a suffix from your Hibernate entity's name.
 * It uses the {@link CamelCaseToUnderscoresNamingStrategy} to transform the name to snake_case.
 * <p>
 * The default suffix is "Entity" but you can change it by passing a new suffix in the constructor.
 */
public class RemoveTableSuffixNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {

    public static final String ENTITY_SUFFIX = "Entity";

    private final String suffix;

    /**
     * Default constructor with the default suffix "Entity".
     */
    public RemoveTableSuffixNamingStrategy() {
        this(ENTITY_SUFFIX);
    }

    /**
     * Constructor with a custom suffix.
     *
     * @param suffix the suffix to remove from the table name
     */
    public RemoveTableSuffixNamingStrategy(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment context) {
        // remove the suffix from the table name
        Identifier identifierWithoutSuffix = removeSuffix(identifier);
        // transform the name to snake_case
        return super.toPhysicalTableName(identifierWithoutSuffix, context);
    }

    private Identifier removeSuffix(Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        String identifierText = identifier.getText();
        String newName = identifierText.endsWith(suffix)
                ? identifierText.substring(0, identifierText.length() - suffix.length())
                : identifierText;
        return Identifier.toIdentifier(newName, identifier.isQuoted());
    }
}
