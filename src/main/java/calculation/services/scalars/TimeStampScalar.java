package calculation.services.scalars;

import graphql.language.IntValue;
import graphql.language.Value;
import graphql.schema.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

public class TimeStampScalar {
    public static final GraphQLScalarType GraphQLTimestamp;

    static {
        Coercing<Timestamp, Timestamp> timeStampCoercing = new Coercing<>() {

            private Timestamp convertImpl(Object input) {
                if (input instanceof Timestamp) {
                    return (Timestamp) input;
                }
                return null;

            }

            @Override
            public Timestamp serialize(Object input) {
                Timestamp result = convertImpl(input);
                if (result == null) {
                    throw new CoercingSerializeException(
                            "Expected type 'Timestamp' but was '" + typeName(input) + "'."
                    );
                }
                return result;
            }

            @Override
            public Timestamp parseValue(Object input) {
                Timestamp result = convertImpl(input);
                if (result == null) {
                    throw new CoercingParseValueException(
                            "Expected type 'Timestamp' but was '" + typeName(input) + "'."
                    );
                }
                return result;
            }

            @Override
            public Timestamp parseLiteral(Object input) {
                if (input instanceof Timestamp) {
                    return (Timestamp) input;
                }
                throw new CoercingParseLiteralException(
                        "Expected AST type 'IntValue', 'StringValue' or 'FloatValue' but was '" + typeName(input) + "'."
                );
            }

            @Override
            public Value<?> valueToLiteral(Object input) {
                Timestamp result = Objects.requireNonNull(convertImpl(input));
                return IntValue.newIntValue(BigInteger.valueOf(result.getTime())).build();
            }

        };

        GraphQLTimestamp = GraphQLScalarType.newScalar()
                .name("Timestamp").description("An arbitrary precision signed decimal")
                .coercing(timeStampCoercing).build();
    }

    private static String typeName(Object input) {
        if (input == null) {
            return "null";
        }

        return input.getClass().getSimpleName();
    }
}
