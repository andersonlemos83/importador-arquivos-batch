package br.com.dbccompany.importadorarquivosbatch.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Cliente;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;

import java.util.ArrayList;
import java.util.List;

public final class RegistroFixture {

    private RegistroFixture() {
    }

    public static List<Registro> umaListaDeRegistrosCom10Vendedores5Clientes20Vendas() {
        List<Registro> registros = new ArrayList<>();
        registros.addAll(obterDezVendedores());
        registros.addAll(obterCincoClientes());
        registros.addAll(obterVinteVendas());
        return registros;
    }

    public static List<Registro> umaListaDeRegistrosComUmaVendaDe8000IhUmaVendaDe2000IhUmaVendaDe1000() {
        List<Registro> registros = new ArrayList<>();
        registros.add(VendaFixture.umaVenda01());
        registros.add(VendaFixture.umaVenda07());
        registros.add(VendaFixture.umaVenda10());
        return registros;
    }

    public static List<Registro> umaListaDeRegistrosComUmaVendaDe7000IhUmaVendaDe2000IhUmaVendaDe100() {
        List<Registro> registros = new ArrayList<>();
        registros.add(VendaFixture.umaVenda06());
        registros.add(VendaFixture.umaVenda08());
        registros.add(VendaFixture.umaVenda19());
        return registros;
    }

    public static List<Registro> umaListaDeRegistrosSemVendasComUmVendedorComSalario10000IhUmVendedorComSalario6000() {
        List<Registro> registros = new ArrayList<>();
        registros.add(VendedorFixture.umVendedorMortenHarket());
        registros.add(VendedorFixture.umVendedorSimonLeBon());
        return registros;
    }

    public static List<Registro> umaListaDeRegistrosComUmVendedorSemVendasIhUmVendedorComUmaVendaQualquer() {
        List<Registro> registros = new ArrayList<>();
        registros.add(VendedorFixture.umVendedorRussellHitchcock());
        registros.add(VendedorFixture.umVendedorDoloresORiordan());
        registros.add(VendaFixture.umaVenda06());
        return registros;
    }

    public static List<Registro> umaListaDeRegistrosComUmVendedorCom9000EmVendasIhUmVendedorCom7000EmVendas() {
        List<Registro> registros = new ArrayList<>();
        registros.add(VendedorFixture.umVendedorBryanAdams());
        registros.add(VendedorFixture.umVendedorDoloresORiordan());
        registros.add(VendaFixture.umaVenda03());
        registros.add(VendaFixture.umaVenda04());
        registros.add(VendaFixture.umaVenda06());
        return registros;
    }

    private static List<Vendedor> obterDezVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(VendedorFixture.umVendedorMortenHarket());
        vendedores.add(VendedorFixture.umVendedorBryanAdams());
        vendedores.add(VendedorFixture.umVendedorAgnethaFaltskog());
        vendedores.add(VendedorFixture.umVendedorDoloresORiordan());
        vendedores.add(VendedorFixture.umVendedorSimonLeBon());
        vendedores.add(VendedorFixture.umVendedorJonBonJovi());
        vendedores.add(VendedorFixture.umVendedorRussellHitchcock());
        vendedores.add(VendedorFixture.umVendedorCyndiLauper());
        vendedores.add(VendedorFixture.umVendedorLauraPausini());
        vendedores.add(VendedorFixture.umVendedorKlausMeine());
        return vendedores;
    }

    private static List<Cliente> obterCincoClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(ClienteFixture.umClienteOi());
        clientes.add(ClienteFixture.umClienteMaceioShopping());
        clientes.add(ClienteFixture.umClienteLivrariaSaraiva());
        clientes.add(ClienteFixture.umClienteMixpel());
        clientes.add(ClienteFixture.umClienteIronStudio());
        return clientes;
    }

    private static List<Venda> obterVinteVendas() {
        List<Venda> vendas = new ArrayList<>();
        vendas.add(VendaFixture.umaVenda01());
        vendas.add(VendaFixture.umaVenda02());
        vendas.add(VendaFixture.umaVenda03());
        vendas.add(VendaFixture.umaVenda04());
        vendas.add(VendaFixture.umaVenda05());
        vendas.add(VendaFixture.umaVenda06());
        vendas.add(VendaFixture.umaVenda07());
        vendas.add(VendaFixture.umaVenda08());
        vendas.add(VendaFixture.umaVenda09());
        vendas.add(VendaFixture.umaVenda10());
        vendas.add(VendaFixture.umaVenda11());
        vendas.add(VendaFixture.umaVenda12());
        vendas.add(VendaFixture.umaVenda13());
        vendas.add(VendaFixture.umaVenda14());
        vendas.add(VendaFixture.umaVenda15());
        vendas.add(VendaFixture.umaVenda16());
        vendas.add(VendaFixture.umaVenda17());
        vendas.add(VendaFixture.umaVenda18());
        vendas.add(VendaFixture.umaVenda19());
        vendas.add(VendaFixture.umaVenda20());
        return vendas;
    }
}