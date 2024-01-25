package com.example.eurekarserver.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.eurekarserver.exception.AccountNotFoundException;
import com.example.eurekarserver.model.Account;

import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;


@Component(value = "accountRepository")
@EnableTransactionManagement
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepository{
	
	private final SessionFactory sessionFactory;

	@Override
	public Account insertAccount(Account account) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.persist(account);
		session.getTransaction().commit();
		return account;
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		TypedQuery<Account> query = session.createQuery("FROM Account A", Account.class);
		return query.getResultList();
	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException{
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Account account = session.get(Account.class, accountNumber);
		if(account == null)
			throw new AccountNotFoundException("account with "+accountNumber+" not found.");
		return account;
	}

	@Override
	public Account updateAccountByAccountNumber(String accountNumber, Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		Account tempAccount = getAccountByAccountNumber(accountNumber);
		if (tempAccount == null) {
			throw new AccountNotFoundException("account with " + accountNumber + " not found");
		}
		Session session = sessionFactory.openSession();
		tempAccount.setAccountHolderName(account.getAccountHolderName());
		tempAccount.setAccountHolderAddress(account.getAccountHolderAddress());
		tempAccount.setEmail(account.getEmail());
		session.getTransaction().begin();
		session.merge(tempAccount);
		session.getTransaction().commit();
		return tempAccount;
		
	}

	@Override
	public void deleteAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		Account tempAccount = getAccountByAccountNumber(accountNumber);
		if (tempAccount == null) {
			throw new AccountNotFoundException("account with " + accountNumber + " not found");
		}
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.remove(session.merge(tempAccount));
		session.getTransaction().commit();		
	}

	@Override
	public Account getAccountByEmail(String email) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		TypedQuery<Account> typedQuery = session.createQuery("From Account a where a.email = :e", Account.class);
		typedQuery.setParameter("e", email);
		if(typedQuery.getResultList().size() == 0)
			throw new AccountNotFoundException("account with email: "+email+" not found.");
		return typedQuery.getSingleResult();
	}	
}
