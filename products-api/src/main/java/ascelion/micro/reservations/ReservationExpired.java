package ascelion.micro.reservations;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationExpired implements Serializable {
	static public final String QUEUE_NAME = "RESERVATION_EXPIRED";

	@NotNull
	private UUID productId;
	@NotNull
	private UUID ownerId;
}