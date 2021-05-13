param location string = 'switzerlandnorth'

resource appsvc 'Microsoft.Web/serverfarms@2020-12-01' = {
  name: 'apple-id-boot'
  location: location
  sku: {
    name: 'S1'
  }
}

resource webapp1 'Microsoft.Web/sites@2020-12-01' = {
  name: 'appleidbootwa'
  location: location
  kind: 'app'
  dependsOn: [
    appsvc
  ]
  properties: {
    serverFarmId: appsvc.id
  }
}
