/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Lucas Falcão
 */
public class TelaCadastroFuncionario extends JFrame implements ActionListener{
    private JLabel bemVindo;
	private JLabel lbCadastro;
	private JLabel lbMatricula;
        private JLabel lbSalario;
        private JLabel lbCPF;
	private JLabel lbNome;
	private JLabel lbCargo;
	private JLabel lbDataNascimento;
	private JLabel lbTelefone;
	private JButton btCadastro;
	private JButton btCancelar;
	private JButton btSair;
	private JTextField tfNome;
	private JTextField tfMatricula;
	private JTextField tfTelefone;
	private JTextField tfDataNascimento;
        private JTextField tfSalario;
        private JTextField tfCPF;
	private JComboBox<Cargo> bjCargos;
	private Funcionario f;

	public TelaCadastroFuncionario() {
		this.inic();
	}

	public TelaCadastroFuncionario(Funcionario funcionario){
		this.f = funcionario;
		this.inic();
		this.edita();
	}

	
	public void inic() {
//		Criando Container e Layout
		Container container = this.getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

//		Instanciando Botões, Labels e afins e botando o texto
		if (this.f == null) {
			this.bemVindo = new JLabel("Cadastro de Funcionários");
		} else {
			this.bemVindo = new JLabel("Edição de Funcionários");
		}
		this.lbMatricula = new JLabel("Digite a Matricula");
		this.tfMatricula = new JTextField();
		this.lbNome = new JLabel("Digite o Nome");
		this.tfNome = new JTextField();
		this.lbTelefone = new JLabel("Digite o Telefone");
		this.tfTelefone = new JTextField();
		this.lbDataNascimento = new JLabel("Digite a Data de Nascimento");
		this.tfDataNascimento = new JTextField();
                this.lbSalario = new JLabel("Digite o Salario");
		this.tfSalario = new JTextField();
		this.lbCPF = new JLabel("Digite o CPF");
		this.tfCPF = new JTextField();
		this.lbCargo = new JLabel("Escolha o Cargo");
		this.bjCargos = new JComboBox<>();
		if (this.f == null) {
			this.btCadastro = new JButton("Cadastrar");
			this.btCadastro.setActionCommand("Cadastrar");
			this.btCadastro.addActionListener(this);
		} else {
			this.btCadastro = new JButton("Editar");
			this.btCadastro.setActionCommand("Editar");
			this.btCadastro.addActionListener(this);
		}
		this.btCancelar = new JButton("Cancelar");
		this.btSair = new JButton("Sair");

//		Configurando ações nos botões
		this.btCancelar.setActionCommand("Cancelar");
		this.btCancelar.addActionListener(this);

		this.btSair.setActionCommand("Sair");
		this.btSair.addActionListener(this);

//		Criando componentes na tela
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridy = 0;
		container.add(this.bemVindo, constraints);
		if (this.f == null) {
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 1;
			container.add(this.lbMatricula, constraints);
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 1;
			constraints.gridy = 1;
			container.add(this.tfMatricula, constraints);
		}
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 2;
		container.add(this.lbNome, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 2;
		container.add(this.tfNome, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 3;
		container.add(this.lbTelefone, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 3;
		container.add(this.tfTelefone, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 4;
		container.add(this.lbDataNascimento, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 4;
		container.add(this.tfDataNascimento, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 5;
		container.add(this.lbCargo, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 5;
		container.add(this.bjCargos, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 6;
		container.add(this.btCadastro, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 7;
		container.add(this.btSair, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 7;
		container.add(this.btCancelar, constraints);
                constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 8;
		container.add(this.lbSalario, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 8;
		container.add(this.tfSalario, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 9;
		container.add(this.lbCPF, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 9;
		container.add(this.tfCPF, constraints);


//		configurando o layout
		this.setSize(800,600);

//		Fechar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void limpa(){
		this.tfDataNascimento.setText(null);
		this.tfMatricula.setText(null);
		this.tfNome.setText(null);
		this.tfTelefone.setText(null);
	}

	public void edita(){
		this.tfDataNascimento.setText(""+this.f.getNascimento());
		this.bjCargos.setSelectedItem(this.f.getCargo());
		this.tfMatricula.setText(this.f.getMatricula()+"");
		this.tfNome.setText(this.f.getNome());
		this.tfTelefone.setText(this.f.getTelefone()+"");
                this.tfSalario.setText(this.f.getSalario()+"");
                this.tfTelefone.setText(this.f.getCpf()+"");
	}
	
	public void atualizaLista() {
	}

	
	public void sair() {
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cadastrar")) {
			if(this.bjCargos.getSelectedItem().equals(Cargo.COMUM)){
				this.setVisible(false);
				ControladorFuncionario.getInstance().exibeTelaPermissões(Integer.parseInt(this.tfMatricula.getText()), this.tfNome.getText(), Integer.parseInt(this.tfDataNascimento.getText()), Integer.parseInt(this.tfTelefone.getText()), (Cargo) this.bjCargos.getSelectedItem());
			} else {
				String msg = ControladorFuncionario.getInstance().cadastraFuncionario(Integer.parseInt(this.tfMatricula.getText()), this.tfNome.getText(), Integer.parseInt(this.tfDataNascimento.getText()), Integer.parseInt(this.tfTelefone.getText()), (Cargo) this.bjCargos.getSelectedItem());
				this.limpa();
				JOptionPane.showMessageDialog(null, msg);
			}
			this.setVisible(false);

			ControladorFuncionario.getInstance().exibeTelaFuncionario();
		}
		if (e.getActionCommand().equals("Editar")) {
			String msg = ControladorFuncionario.getInstance().alteraFuncionario(Integer.parseInt(this.tfMatricula.getText()), this.tfNome.getText(), Integer.parseInt(this.tfDataNascimento.getText()), Integer.parseInt(this.tfTelefone.getText()), (Cargo) this.bjCargos.getSelectedItem());
			JOptionPane.showMessageDialog(null, msg);
			this.limpa();
			this.setVisible(false);
			ControladorFuncionario.getInstance().exibeTelaFuncionario();
		}
		if(e.getActionCommand().equals("Cancelar")){
			this.setVisible(false);
			ControladorFuncionario.getInstance().exibeTelaFuncionario();
		}
		if (e.getActionCommand().equals("Sa")) {
			this.sair();
		}
	}

}
