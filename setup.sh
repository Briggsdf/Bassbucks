################################################################################
# Help                                                                         #
################################################################################
Help()
{
   # Display Help
   echo "Welcome to Code World Secrets Setup(CWS-Setup)"
   echo
   echo "Syntax: cwssetup [-p|t]"
   echo "options:"
   echo "p     Set the path for secrets retrival  kv/secret/bassbucks-dev"
   echo "t     Github Personal Access Token if you need to authenticate."
   echo
}


while getopts p:t: flag
do
    case "${flag}" in
        p) secretpath=${OPTARG};;
        t) token=${OPTARG};;
    esac
done

Help


export VAULT_ADDR='https://cws-vault.vault.ac109015-b0c0-49e7-abeb-4c81ad477809.aws.hashicorp.cloud:8200'
export VAULT_NAMESPACE="admin"
if [ -z $token ]; then echo "skipping github auth";
else vault_token=$(vault login -token-only -method=github token=$token)
export VAULT_TOKEN=$vault_token;
fi

vault kv get -format "json" $secretpath | dd of=application.json
mv application.json src/main/resources/application.json
