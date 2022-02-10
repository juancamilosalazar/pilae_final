package co.edu.uco.mensajes.api.inicializador;

import co.edu.uco.mensajes.dto.AplicacionDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@SpringBootApplication
@ComponentScan(basePackages = { "co.edu.uco.mensajes" })
public class MensajesApiApplication {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuracionRedis = new RedisStandaloneConfiguration();
		configuracionRedis.setHostName("redis-18171.c289.us-west-1-2.ec2.cloud.redislabs.com");
		configuracionRedis.setPort(18171);
		configuracionRedis.setDatabase(0);
		configuracionRedis.setPassword(RedisPassword.of("WhY2HQDv5i4JvbQUYtltf7aBClLvPSnB"));

		JedisClientConfiguration.JedisClientConfigurationBuilder configuracionJedis = JedisClientConfiguration.builder();
		configuracionJedis.connectTimeout(Duration.ofSeconds(60));

		return new JedisConnectionFactory(configuracionRedis, configuracionJedis.build());
	}

	@Bean
	public RedisTemplate<String, AplicacionDTO> redisTemplate() {
		RedisTemplate<String, AplicacionDTO> plantillaRedis = new RedisTemplate<>();
		plantillaRedis.setConnectionFactory(jedisConnectionFactory());
		return plantillaRedis;
	}

	public static void main(String[] args) {
		SpringApplication.run(MensajesApiApplication.class, args);
	}

}
