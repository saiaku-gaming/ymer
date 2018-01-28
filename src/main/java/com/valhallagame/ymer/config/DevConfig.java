package com.valhallagame.ymer.config;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.chatserviceclient.ChatServiceClient;
import com.valhallagame.featserviceclient.FeatServiceClient;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.notificationserviceclient.NotificationServiceClient;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.statisticsserviceclient.StatisticsServiceClient;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;
import com.valhallagame.ymer.GenerateMessages;

@Configuration
@Profile("default")
public class DevConfig {

	private static final int RESTART_PORT = 55557;

	@PostConstruct
	public void init() throws IOException, InterruptedException {
		GenerateMessages.generateSeviceMessages();

		Socket s = new Socket();
		try {
			s.connect(new InetSocketAddress("localhost", RESTART_PORT));
			Thread.sleep(100);
		} catch (ConnectException e) {
			// nothing to restart - ignore
		} finally {
			s.close();
		}
		startRestartThread();
	}

	@Bean
	public CharacterServiceClient characterServiceClient() {
		return CharacterServiceClient.get();
	}

	@Bean
	public PersonServiceClient personServiceClient() {
		return PersonServiceClient.get();
	}

	@Bean
	public ChatServiceClient chatServiceClient() {
		return ChatServiceClient.get();
	}

	@Bean
	public FeatServiceClient featServiceClient() {
		return FeatServiceClient.get();
	}

	@Bean
	public InstanceServiceClient instanceServiceClient() {
		return InstanceServiceClient.get();
	}

	@Bean
	public PartyServiceClient partyServiceClient() {
		return PartyServiceClient.get();
	}

	@Bean
	public NotificationServiceClient notificationServiceClient() {
		return NotificationServiceClient.get();
	}

	@Bean
	public WardrobeServiceClient wardrobeServiceClient() {
		return WardrobeServiceClient.get();
	}

	@Bean
	public FriendServiceClient friendServiceClient() {
		return FriendServiceClient.get();
	}

	@Bean
	public StatisticsServiceClient statisticsServiceClient() {
		return StatisticsServiceClient.get();
	}

	@Bean
	public TraitServiceClient traitServiceClient() {
		return TraitServiceClient.get();
	}

	private static void startRestartThread() {
		// TODO Security?
		new Thread() {

			@Override
			public void run() {
				try {
					ServerSocket s = new ServerSocket(RESTART_PORT);
					try {
						s.accept();
					} finally {
						s.close();
					}
				} catch (Exception e) {
					// S.error("", e);
				}
				System.exit(0);
			}
		}.start();
	}
}
