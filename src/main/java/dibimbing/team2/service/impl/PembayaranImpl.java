package dibimbing.team2.service.impl;

import dibimbing.team2.dao.TransaksiRequest;
import dibimbing.team2.model.Barang;
import dibimbing.team2.model.Pembayaran;
import dibimbing.team2.model.Pembeli;
import dibimbing.team2.model.Transaksi;
import dibimbing.team2.model.oauth.User;
import dibimbing.team2.repository.BarangRepository;
import dibimbing.team2.repository.PembayaranRepository;
import dibimbing.team2.repository.PembeliRepository;
import dibimbing.team2.repository.TransaksiRepository;
import dibimbing.team2.repository.oauth.UserRepository;
import dibimbing.team2.service.PembayaranService;
import dibimbing.team2.service.TransaksiService;
import dibimbing.team2.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class PembayaranImpl implements PembayaranService {

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public PembayaranRepository pembayaranRepository;

    @Autowired
    public  TransaksiRepository transaksiRepository;


    @Override
    public Map createOrder(Pembayaran pembayaran) {
        try{
            Transaksi obj = transaksiRepository.getbyID(pembayaran.getTransaksi().getId());
            if(obj != null){
                return templateResponse.templateEror("Transaksi id tidak di temukan!");
            }
            pembayaran.setStatus("menunggu");
            pembayaran.setTransaksi(pembayaran.getTransaksi());
            pembayaran.setBuktiTrf(pembayaran.getBuktiTrf());
            pembayaran.setNoOrder(String.valueOf(UUID.randomUUID()));
            pembayaran.setTotalBayar(pembayaran.getTotalBayar());

            Pembayaran doSave = pembayaranRepository.save(pembayaran);
            obj.setStatus("dipesan");
            transaksiRepository.save(obj);

            return templateResponse.templateSukses(doSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map uploadBuktiBayar(Pembayaran pembayaran) {
        try{
            Transaksi obj = transaksiRepository.getbyID(pembayaran.getTransaksi().getId());
            if(obj != null){
                return templateResponse.templateEror("Transaksi id tidak di temukan!");
            }
            pembayaran.setStatus("menunggu");
            pembayaran.setTransaksi(pembayaran.getTransaksi());
            pembayaran.setBuktiTrf(pembayaran.getBuktiTrf());
            pembayaran.setNoOrder(String.valueOf(UUID.randomUUID()));
            pembayaran.setTotalBayar(pembayaran.getTotalBayar());

            Pembayaran doSave = pembayaranRepository.save(pembayaran);
            obj.setStatus("dipesan");
            transaksiRepository.save(obj);

            return templateResponse.templateSukses(doSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map approvalBuktiBayar(Pembayaran pembayaran) {
        try{
            Transaksi obj = transaksiRepository.getbyID(pembayaran.getTransaksi().getId());
            if(obj != null) {
                return templateResponse.templateEror("Transaksi id tidak di temukan!");
            }
            pembayaran.setTransaksi(pembayaran.getTransaksi());
            pembayaran.setBuktiTrf(pembayaran.getBuktiTrf());
            pembayaran.setNoOrder(String.valueOf(UUID.randomUUID()));
            pembayaran.setTotalBayar(pembayaran.getTotalBayar());
            pembayaran.setStatus("lunas");
            Pembayaran doSave = pembayaranRepository.save(pembayaran);
            obj.setStatus("dibayar");
            transaksiRepository.save(obj);

            return templateResponse.templateSukses(doSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }
}
