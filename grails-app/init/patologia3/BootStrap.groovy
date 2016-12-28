package patologia3

import com.security.*
import com.model.*

class BootStrap {

    def init = { servletContext ->

    	println "Iniciando"
    	if(Role.count()==0){

			def adminRole= new Role(authority: "ROLE_ADMIN")
			// adminRole.addToPermissions("*:*")
			adminRole.save(flush:true, failOnError:true)
			
			def patologoRole = new Role(authority:"ROLE_PATOLOGO")
			// patologoRole.addToPermissions("patologoProfesional:*")
			patologoRole.save(flush:true, failOnError:true)
			
			def citologoRole= new Role(authority: "ROLE_CITOLOGO")
			// citologoRole.addToPermissions("citologo:*")
			citologoRole.save(flush:true, failOnError:true)
			
			def secretariaRole= new Role(authority: "ROLE_SECRETARIA")
			// secretariaRole.addToPermissions("secretaria:*")
			secretariaRole.save(flush:true, failOnError:true)
			
			def residenteRole= new Role(authority: "ROLE_RESIDENTE")
			// residenteRole.addToPermissions("residente:*")
			residenteRole.save(flush:true, failOnError:true)
			
			def histotecnologoRole= new Role(authority: "ROLE_HISTOTECNOLOGO")
			// histotecnologoRole.addToPermissions("histotecnologo:*")
			 histotecnologoRole.save(flush:true, failOnError:true)
			
			def admin = new User(username: "admin@test.com", password: "password", profesion: "Administrador")
			admin.save(flush:true,failOnError:true)
			UserRole.create(admin, adminRole)
		   
			def patologo = new User(username: "patologo@test.com", password: "password", profesion: "Patologo")
			patologo.save(flush:true,failOnError:true)
			UserRole.create(patologo, patologoRole)

			
			def citologo = new User(username: "citologo@test.com", password: "password", profesion: "Citologo")
			citologo.save(flush:true, failOnError:true)
			UserRole.create(citologo, citologoRole)
			
			def residente = new User(username: "residente@test.com", password: "password", profesion: "Residente")
			residente.save(flush:true,failOnError:true)
			UserRole.create(residente, residenteRole)
			
			def secretaria = new User(username: "secretaria@test.com", password: "password", profesion: "Secretaria")
			secretaria.save(flush:true,failOnError:true)
			UserRole.create(secretaria, secretariaRole)

			
			def histotecnologo = new User(username: "histotecnologo@test.com", password: "password", profesion: "Histotecnologo")
			histotecnologo.save(flush:true,failOnError:true)
			UserRole.create(histotecnologo, histotecnologoRole)

			
			def  usuarioProfesionalAdmin = new Administrador(apellidos: "Sanchez Merchan",nombres: "Angel Yobanny",email: admin.username,secUser: admin,telefonoCelular: "3006321855",telefonoFijo: "3712652")
			usuarioProfesionalAdmin.save(flush:true, failOnError:true)
			
			
			def  usuarioProfesionalPatologo = new PatologoProfesional(apellidos: "Donato Ariza",nombres: "Nestor Andres",email: patologo.username,secUser: patologo,registroMedico: "1234",telefonoCelular: "3006321855",telefonoFijo: "3712652")
			usuarioProfesionalPatologo.save(flush:true, failOnError:true)
			
			
			def  usuarioProfesionalCitologo = new Citologo(apellidos: "Gonzales perez",nombres: "Mario",email: citologo.username,profesion: "Citologo",secUser: citologo,registroMedico: "1234",telefonoCelular: "3006321855",telefonoFijo: "3712652")
			usuarioProfesionalCitologo.save(flush:true, failOnError:true)
			
			
			def  usuarioProfesionalResidente = new Residente(apellidos: "Cruz Salinas",nombres: "Andres Felipe",email: residente.username,secUser: residente,telefonoCelular: "3006321855",telefonoFijo: "3712652")
			usuarioProfesionalResidente.save(flush:true, failOnError:true)
			
			
			def  usuarioProfesionalHistotecnologo = new Histotecnologo(apellidos: "Restrepo Ramirez",nombres: "Paola Andrea",email: histotecnologo.username,secUser: histotecnologo,telefonoCelular: "3006321855",telefonoFijo: "3712652")
			usuarioProfesionalHistotecnologo.save(flush:true, failOnError:true)
			
			
			def  usuarioProfesionalSecretaria = new Secretaria(apellidos: "Fernandez Florez",nombres: "Pedro Luis",email: secretaria.username,secUser: secretaria,telefonoCelular: "3006321855",telefonoFijo: "3712652")
			usuarioProfesionalSecretaria.save(flush:true, failOnError:true)
			
			println "Terminando"
		}
    }
    def destroy = {
    }
}
