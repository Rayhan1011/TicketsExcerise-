package thirdparty.seatbooking;

public interface SeatReservationService {

    boolean reserveSeat(long accountId, int totalSeatsToAllocate);

}
