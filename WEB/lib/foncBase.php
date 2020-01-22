<?php

function choixAlert($message, $arg= null )
{
  $alert = array();
	  switch($message)
	  {
	    case 'query' :
	      $alert['messageAlert'] = ERREUR_QUERY;
	      break;
		case 'categorie' :
	      $alert['messageAlert'] = ERREUR_CATEGORIE;
	      break;
	    case 'url_non_valide' :
	      $alert['messageAlert'] = TEXTE_PAGE_404;
	      break;
		case 'erreur_connexion' :
		  $alert['messageAlert'] = ERREUR_CONNEXION;
		  break;
		case 'ok_connexion' :
			$alert['classAlert']= 'success';
		    $alert['messageAlert'] =CONNEXION_OK;
		   break;
		case 'photo' :
		  $alert['messageAlert'] = ERREUR_ID_PHOTO;
		  break;
		case 'login_id' :
	        $alert['messageAlert'] = ERREUR_FORM_CONNEXION_UNKNOWID;
	            break;
	    case 'login_pass' :
	        $alert['messageAlert'] = ERREUR_FORM_CONNEXION_PASSWORD;
	            break;
		case 'erreur_id' :
		  $alert['messageAlert'] = ERREUR_ID;
		  break;
		case 'erreur_mdp' :
		  $alert['messageAlert'] = ERREUR_MDP;
		  break;
		case 'con_vide' :
		  $alert['messageAlert'] = CONNEXION_VIDE;
		   break;

	//Informations
        case 'selectedPhotos':
            $alert['messageAlert'] = $arg." ".TEXTE_SELECTED_PHOTOS;
            $alert["classAlert"] = "success";
            break;
        case 'add_cat':
            $alert['messageAlert'] =TEXTE_FORM_CAT_SUCCESS;
            $alert["classAlert"] = "success";
            break;
        case 'add_photo':
            $alert['messageAlert'] = TEXTE_FORM_PHOTO_SUCCESS;
            $alert["classAlert"] = "success";
            break;
        case 'connexion':
            $alert['messageAlert'] =TEXTE_FORM_CONNEXION_SUCCESS1.$arg.TEXTE_FORM_CONNEXION_SUCCESS2;
            $alert["classAlert"] = "success";
            break;
        case 'inscription':
            $alert['messageAlert'] =TEXTE_FORM_INSCRIPTION_SUCCESS1.$arg.TEXTE_FORM_INSCRIPTION_SUCCESS2;
            $alert["classAlert"] = "success";
            break;
        case 'photo_delete_success':
            $alert['messageAlert'] = TEXTE_PHOTO_DELETE_SUCCESS;
            $alert["classAlert"] = "success";
            break;
        case 'photo_modif_success':
            $alert['messageAlert'] =TEXTE_PHOTO_MODIF_SUCCESS;
            $alert["classAlert"] = "success";
            break;
	 
    default :
      $alert['messageAlert'] = MESSAGE_ERREUR;
  }
  return $alert;
}
