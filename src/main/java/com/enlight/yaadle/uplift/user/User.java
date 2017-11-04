package com.enlight.yaadle.uplift.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements Serializable, UserDetails{
	
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
	  
	  public User(String token) {
		  super();
		  this.setToken(token);
	  }
	  
	  public User(String username, String password, boolean enabled, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,List<GrantedAuthority> authorities) {
       super();
       this.username=username;
       this.password=password;
       this.enabled=enabled;
       this.isAccountNonExpired=isAccountNonExpired;
       this.isAccountNonLocked=isAccountNonLocked;
       this.isCredentialsNonExpired =isCredentialsNonExpired;
       authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
       this.authorities=authorities;
	  }

	  public User(String username, String password, List<GrantedAuthority> authorities) {
		super();
		this.username=username;
	    this.password=password;
	    this.authorities=authorities;
	}

	  private String username="";
	  private String password="";
	  private String token="";
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
 	  private String skey="";
 	  private boolean enabled=false;
 	  private boolean isAccountNonExpired=true;
 	  private String role;
 	  private List<? extends GrantedAuthority> authorities=null;
	  private boolean isAccountNonLocked=true;
 	  private boolean isCredentialsNonExpired=true;
 	  
 	  public boolean isAccountNonExpired()                                  {   return isAccountNonExpired;       }
	  public boolean isAccountNonLocked()                                   { 	return isAccountNonLocked;	      }
	  public boolean isCredentialsNonExpired()                              {	return isCredentialsNonExpired;	  }

	  public void setCredentialsNonExpired(boolean isCredentialsNonExpired) { 	this.isCredentialsNonExpired = isCredentialsNonExpired;	}
	  public void setAccountNonLocked(boolean isAccountNonLocked)           { 	this.isAccountNonLocked = isAccountNonLocked;           }
	  public void setAccountNonExpired(boolean isAccountNonExpired)         { 	this.isAccountNonExpired = isAccountNonExpired; 	    }
	  
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
 	  public String getProfileurl() {	return profileUrl; }
 	  public String getKey()        { 	return skey;       }
 	  public String getToken()      { 	return token;	   }
 	  
 	  public void setFamilyName(String familyName) 	     				  { this.familyName = familyName;	}
 	  public void setLogin(String login) 			 	 				  {	this.login = login;				}
 	  public void setKey(String skey) 			 	 				      {	this.skey = skey;				}
 	  public void setFirstName(String firstName) 		 				  { this.firstName = firstName;		}
 	  public void setLastName(String lastname) 		 				      { this.lastname = lastname;		}
 	  public void setRole(String role)                                    { this.role = role; 		        }
 	  public void setToken(String token)                                  {	this.token = token; 		    }
 	  public void setProviderId(String providerId)        				  {	this.providerId = providerId;   }
	  public void setUsername(String username)            				  { this.username = username;       }
	  public void setPassword(String password)           				  { this.password = password;       }
	  public void setDisplayname(String displayname)      				  { this.displayname = displayname; }
	  public void setEmail(String email)                  				  { this.email = email;             }
	  public void setAddress(String address)              				  { this.address = address;         }
	  public void setPhone(String phone)                  				  { this.phone = phone;             }
	  public void setStatus(String status)                				  {	this.status = status;		    } 
	  public void setImageUrl(String imageUrl)                            { this.imageUrl=imageUrl;         }
	  public String getRole()            								  { return role;  	                }
 	  public void setProfileUrl(String profileUrl) 						  { this.profileUrl =profileUrl;    }
 	  public void setId(String id)                                        { this.id = id; 				    }
 	  public void setEnabled(boolean enabled)							  { this.enabled= enabled;          }

 	  public void setRole(String role, Collection<? extends GrantedAuthority> roles)          { this.roles=roles;	            }
 	  public void setAuthorities(List<? extends GrantedAuthority> authorities) 	              { this.authorities = authorities;	}
 	  
 	  @Override
 	  public Collection<? extends GrantedAuthority> getAuthorities() {return null;	}

 	  @Override
 	  public boolean isEnabled() { return true; }
	  
 	  @Override
	  public String toString() {
		  return "User [displayname=" + displayname + ", username=" + username + ", login=" + login + ", roles=" + roles + ", role=" + role + ", password=" + password + ", email=" + email + ", address=" + 
				        address + ",phone=" + phone +", familyName=" + familyName + ", lastname=" + lastname +", status=" + status + ", id=" + id + ", imageUrl=" + imageUrl + 
				        ", authorities=" + authorities + ", providerId=" + providerId + ", token=" + token + ", profileUrl=" + profileUrl +"]";
	  }

}
