package com.enlight.yaadle.uplift.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class User implements Serializable, Collection<User>{
	
	private static final long serialVersionUID = 1L;
	
	  public User() {
		  super();
		  this.enabled=false;
	  } 
	  
	  public User(String email, String username, String password) {
		  super();
		  this.email=email;
		  this.username=username;
		  this.password=password;
	  }

	  public User(String username, String password) {
		  super();
		  this.username=username;
		  this.password=password;
	  }
	  
	  private String username="";
	  private String password="";
	  private String displayname="";
	  private String email="";
	  private String address="";
	  private String status="";
	  private Collection<? extends GrantedAuthority> roles=null;
	  private String phone="";
	  private String imageUrl="";
	  private String profileUrl="";
  	  private String providerId="";
  	  private String login="";
  	  private String id="";
  	  private String firstName="";
 	  private String familyName="";
 	  private String lastname="";
 	  private boolean enabled=false;
 	  private Set<String> authorities = new HashSet<String>();

	  public String getUsername()   {  return username;    }
	  public String getPassword()   {  return password;    }
	  public String getDisplayname(){  return displayname; }
	  public String getEmail()      {  return email;       }
	  public String getAddress()    {  return address;     }
	  public String getPhone()      {  return phone;       }
	  public String getimageUrl()   {  return imageUrl;    }
	  public String getFirstName()  {	return firstName;  }
 	  public String getFamilyName() {	return familyName; }
 	  public String getLogin() 	    {	return login;	   }
 	  public boolean getEnabled() 	{	return enabled;	   }
 	  public Object getLastName()   {   return lastname;   }
 	  
 	  public void setFamilyName(String familyName) 	     				  { this.familyName = familyName;	}
 	  public void setLogin(String login) 			 	 				  {	this.login = login;				}
 	  public void setFirstName(String firstName) 		 				  { this.firstName = firstName;		}
 	  public void setLastName(String lastname) 		 				      { this.lastname = lastname;		}
	  public Set<String> getAuthorities()                 				  { return authorities; 	        }
 	  public void setAuthorities(Set<String> authorities) 				  { this.authorities = authorities;	}
 	  public void setProviderId(String providerId)        				  {	this.providerId = providerId;   }
	  public void setUsername(String username)            				  { this.username = username;       }
	  public void setPassword(String password)           				  { this.password = password;       }
	  public void setDisplayname(String displayname)      				  { this.displayname = displayname; }
	  public void setEmail(String email)                  				  { this.email = email;             }
	  public void setAddress(String address)              				  { this.address = address;         }
	  public void setPhone(String phone)                  				  { this.phone = phone;             }
	  public void setStatus(String status)                				  {	this.status = status;		    } 
	  public void setImageUrl(String imageUrl)                            { this.imageUrl=imageUrl;         }
	  public Collection<? extends GrantedAuthority> getRole()             { return roles;  	                }
 	  public void setProfileUrl(String profileUrl) 						  { this.profileUrl =profileUrl;    }
 	  public void setId(String id)                                        { this.id = id; 				    }
 	  public void setEnabled(boolean enabled)							  { this.enabled= enabled;          }
 	  public void setRole(String role, Collection<? extends GrantedAuthority> roles)                                    { this.roles=roles;	            }
 	  
	  @Override
	  public String toString() {
		  return "User [displayname=" + displayname + ", username=" + username + ", login=" + login + ", roles=" + roles + ", password=" + password + ", email=" + email + ", address=" + 
				        address + ",phone=" + phone +", familyName=" + familyName + ", lastname=" + lastname +", status=" + status + ", id=" + id + ", imageUrl=" + imageUrl + 
				        ", authorities=" + authorities + ", providerId=" + providerId +", profileUrl=" + profileUrl +"]";
	  }

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<User> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(User e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends User> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	


		
	}
