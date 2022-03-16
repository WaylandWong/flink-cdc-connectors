package demo;

import com.ververica.cdc.connectors.postgres.PostgreSQLSource;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class MainClass {
    public static void main(String[] args) throws Exception {
        SourceFunction<String> source = PostgreSQLSource.<String>builder()
                .hostname("host")
                .port(5432)
                .database("databse")
                .schemaList("public")
                .tableList("public.table")
                .username("username")
                .password("password")
                .decodingPluginName("pgoutput")
                .deserializer(new JsonDebeziumDeserializationSchema())
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env
                .addSource(source)
                .print().setParallelism(1);

        env.execute();
    }
}
