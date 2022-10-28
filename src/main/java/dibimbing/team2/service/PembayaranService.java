package dibimbing.team2.service;


import dibimbing.team2.dao.TransaksiRequest;
import dibimbing.team2.model.Pembayaran;

import java.util.Map;

public interface PembayaranService {

    public Map createOrder(Pembayaran pembayaran);
    public Map uploadBuktiBayar(Pembayaran pembayaran);
    public Map approvalBuktiBayar(Pembayaran pembayaran);

}
