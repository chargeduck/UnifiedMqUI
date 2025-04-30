export const gbFilter = (bytes) => {
  if (isBlank(bytes) ) {
    return '0 Gb'
  }
  return `${ (bytes / (1024 * 1024 * 1024)).toFixed(2) } Gb`
}

export const isBlank = (value) => {
  return value === null || value === undefined || value === ''
}
